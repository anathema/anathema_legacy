package net.sf.anathema.campaign.music.presenter.library;

import javax.swing.event.ListSelectionListener;

public interface ILibraryListView {

  void addLibraryListSelectionListener(ListSelectionListener listener);

  Object getSelectedLibrary();
}