package net.sf.anathema.campaign.music.impl.model.library;

import net.sf.anathema.campaign.music.impl.model.tracks.FileMp3Track;
import net.sf.anathema.campaign.music.impl.model.tracks.MusicFolderWalker;
import net.sf.anathema.campaign.music.impl.persistence.MusicDatabasePersister;
import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.libary.ILibraryChangedListener;
import net.sf.anathema.campaign.music.model.libary.ILibraryControl;
import net.sf.anathema.campaign.music.model.libary.IMusicFolderWalker;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.exception.AnathemaException;
import org.jmock.example.announcer.Announcer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class LibraryControl implements ILibraryControl {

  private final Announcer<ILibraryChangedListener> listenerControl = Announcer.to(ILibraryChangedListener.class);
  private MusicDatabasePersister musicDataBasePersister;

  public LibraryControl(MusicDatabasePersister persister) {
    this.musicDataBasePersister = persister;
  }

  @Override
  public void updateLibrary(ILibrary library, String newName) {
    musicDataBasePersister.updateLibaryName(library, newName);
    fireLibraryChanged(newName);
  }

  @Override
  public ILibrary[] getAllLibraries() {
    return musicDataBasePersister.getAllLibraries();
  }

  @Override
  public boolean containsLibraryName(String name) {
    return musicDataBasePersister.isRegisteredLibrary(name);
  }

  @Override
  public void addNewLibrary(String name) {
    musicDataBasePersister.addLibrary(name);
    fireLibraryChanged(name);
  }

  @Override
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

  private void fireLibraryChanged(String name) {
    ILibrary[] allLibraries = getAllLibraries();
    ILibrary newLibrary = getLibraryByName(allLibraries, name);
    listenerControl.announce().librariesChanged(allLibraries, newLibrary);
  }

  protected ILibrary getLibraryByName(ILibrary[] allLibraries, String name) {
    for (ILibrary library : allLibraries) {
      if (library.getName().equals(name)) {
        return library;
      }
    }
    return null;
  }

  @Override
  public void addLibraryChangedListener(ILibraryChangedListener listener) {
    listenerControl.addListener(listener);
  }

  @Override
  public void addTracks(String name, List<IMp3Track> tracks) {
    musicDataBasePersister.addTracks(name, tracks);
  }

  @Override
  public IMusicFolderWalker createMusicFolderWalker(File folder) throws IOException {
    return new MusicFolderWalker(folder);
  }

  @Override
  public void removeLibrary(ILibrary library) {
    musicDataBasePersister.removeLibrary(library.getName());
    fireLibraryChanged(null);
  }

  @Override
  public void removeTracksFromLibrary(ILibrary library, IMp3Track[] tracks) {
    musicDataBasePersister.removeTracksFromLibrary(library.getName(), tracks);
    fireLibraryChanged(library.getName());
  }

  @Override
  public void addTrack(String name, File mp3File) throws AnathemaException, IOException {
    List<IMp3Track> tracks = new ArrayList<>();
    tracks.add(new FileMp3Track(mp3File));
    musicDataBasePersister.addTracks(name, tracks);
  }
}