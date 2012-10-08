package net.sf.anathema.campaign.music.presenter.selection;

import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.gui.list.actionview.IMultiSelectionActionAddableListView;

public class DeleteSelectionRunnable implements Runnable {
  private final IMultiSelectionActionAddableListView<IMp3Track> trackListView;
  private final IMusicSelectionModel selectionModel;

  public DeleteSelectionRunnable(IMultiSelectionActionAddableListView<IMp3Track> trackListView,
                                 IMusicSelectionModel selectionModel) {
    this.trackListView = trackListView;
    this.selectionModel = selectionModel;
  }

  @Override
  public void run() {
    selectionModel.removeFromCurrentSelection(trackListView.getSelectedIndices());
  }
}
