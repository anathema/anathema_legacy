package net.sf.anathema.campaign.music.presenter.library;

import javax.swing.event.ListSelectionListener;

public interface ILibraryListView {

  public void addLibraryListSelectionListener(ListSelectionListener listener);

  public Object getSelectedLibrary();
}