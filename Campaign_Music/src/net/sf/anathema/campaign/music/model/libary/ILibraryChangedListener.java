package net.sf.anathema.campaign.music.model.libary;

public interface ILibraryChangedListener {

  public void librariesChanged(ILibrary[] allLibraries, ILibrary selectedLibrary);
}