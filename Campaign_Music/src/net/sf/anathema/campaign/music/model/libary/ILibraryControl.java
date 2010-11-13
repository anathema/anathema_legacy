package net.sf.anathema.campaign.music.model.libary;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.exception.AnathemaException;

public interface ILibraryControl {

  public void addLibraryChangedListener(ILibraryChangedListener listener);

  public void addNewLibrary(String name);

  public void addTrack(String name, File mp3File) throws AnathemaException, IOException;

  public void addTracks(String name, List<IMp3Track> tracks);

  public boolean containsLibraryName(String name);

  public IMusicFolderWalker createMusicFolderWalker(File folder) throws IOException;

  public ILibrary[] getAllLibraries();

  public void updateLibrary(ILibrary library, String newName);

  public void removeLibrary(ILibrary library);

  public void removeTracksFromLibrary(ILibrary library, IMp3Track[] tracks);

  public void addNewUnnamedLibrary(String unnamedLibraryBaseString);
}