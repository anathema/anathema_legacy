package net.sf.anathema.campaign.music.model.libary;

public interface ILibraryChangedListener {

  void librariesChanged(ILibrary[] allLibraries, ILibrary selectedLibrary);
}