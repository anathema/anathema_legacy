package net.sf.anathema.campaign.music.model.libary;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.exception.AnathemaException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ILibraryControl {

  void addLibraryChangedListener(ILibraryChangedListener listener);

  void addNewLibrary(String name);

  void addTrack(String name, File mp3File) throws AnathemaException, IOException;

  void addTracks(String name, List<IMp3Track> tracks);

  boolean containsLibraryName(String name);

  IMusicFolderWalker createMusicFolderWalker(File folder) throws IOException;

  ILibrary[] getAllLibraries();

  void updateLibrary(ILibrary library, String newName);

  void removeLibrary(ILibrary library);

  void removeTracksFromLibrary(ILibrary library, IMp3Track[] tracks);

  void addNewUnnamedLibrary(String unnamedLibraryBaseString);
}