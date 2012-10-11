package net.sf.anathema.campaign.music.view.library;

public interface LibraryListView {

  Object getSelectedLibrary();

  void whenSelectionChanges(Runnable runnable);
}