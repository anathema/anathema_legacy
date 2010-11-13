package net.sf.anathema.campaign.music.impl.model.library;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.campaign.music.impl.model.tracks.FileMp3Track;
import net.sf.anathema.campaign.music.impl.model.tracks.MusicFolderWalker;
import net.sf.anathema.campaign.music.impl.persistence.MusicDatabasePersister;
import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.libary.ILibraryChangedListener;
import net.sf.anathema.campaign.music.model.libary.ILibraryControl;
import net.sf.anathema.campaign.music.model.libary.IMusicFolderWalker;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.exception.AnathemaException;

public final class LibraryControl implements ILibraryControl {

  private final GenericControl<ILibraryChangedListener> listenerControl = new GenericControl<ILibraryChangedListener>();
  private MusicDatabasePersister musicDataBasePersister;

  public LibraryControl(MusicDatabasePersister persister) {
    this.musicDataBasePersister = persister;
  }

  public void updateLibrary(ILibrary library, String newName) {
    musicDataBasePersister.updateLibaryName(library, newName);
    fireLibraryChanged(newName);
  }

  public ILibrary[] getAllLibraries() {
    return musicDataBasePersister.getAllLibraries();
  }

  public boolean containsLibraryName(String name) {
    return musicDataBasePersister.isRegisteredLibrary(name);
  }

  public void addNewLibrary(final String name) {
    musicDataBasePersister.addLibrary(name);
    fireLibraryChanged(name);
  }

  public void addNewUnnamedLibrary(String unnamedLibraryBaseString) {
    int count = 1;
    ILibrary[] allLibraries = getAllLibraries();
    ILibrary library = getLibraryByName(allLibraries, createLibrarayName(unnamedLibraryBaseString, count));
    while (library != null) {
      count++;
      library = getLibraryByName(allLibraries, createLibrarayName(unnamedLibraryBaseString, count));
    }
    String libraryName = createLibrarayName(unnamedLibraryBaseString, count);
    addNewLibrary(libraryName);
  }

  private String createLibrarayName(String unnamedLibraryBaseString, int count) {
    return unnamedLibraryBaseString + " " + count; //$NON-NLS-1$
  }

  private void fireLibraryChanged(final String name) {
    listenerControl.forAllDo(new IClosure<ILibraryChangedListener>() {
      public void execute(ILibraryChangedListener input) {
        ILibrary[] allLibraries = getAllLibraries();
        ILibrary newLibrary = getLibraryByName(allLibraries, name);
        input.librariesChanged(allLibraries, newLibrary);
      }
    });
  }

  protected ILibrary getLibraryByName(ILibrary[] allLibraries, String name) {
    for (ILibrary library : allLibraries) {
      if (library.getName().equals(name)) {
        return library;
      }
    }
    return null;
  }

  public void addLibraryChangedListener(ILibraryChangedListener listener) {
    listenerControl.addListener(listener);
  }

  public void addTracks(String name, List<IMp3Track> tracks) {
    musicDataBasePersister.addTracks(name, tracks);
  }

  public IMusicFolderWalker createMusicFolderWalker(File folder) throws IOException {
    return new MusicFolderWalker(folder);
  }

  public void removeLibrary(ILibrary library) {
    musicDataBasePersister.removeLibrary(library.getName());
    fireLibraryChanged(null);
  }

  public void removeTracksFromLibrary(ILibrary library, IMp3Track[] tracks) {
    musicDataBasePersister.removeTracksFromLibrary(library.getName(), tracks);
    fireLibraryChanged(library.getName());
  }

  public void addTrack(String name, File mp3File) throws AnathemaException, IOException {
    List<IMp3Track> tracks = new ArrayList<IMp3Track>();
    tracks.add(new FileMp3Track(mp3File));
    musicDataBasePersister.addTracks(name, tracks);
  }
}