package net.sf.anathema.campaign.music.presenter.selection;

import java.awt.Component;

import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.presenter.util.AbstractTrackSelectionAction;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.gui.list.actionview.IMultiSelectionActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

public class DeleteSelectionTracksAction extends AbstractTrackSelectionAction {

  private final IMusicSelectionModel selectionModel;
  private final IMultiSelectionActionAddableListView<IMp3Track> trackListView;

  public DeleteSelectionTracksAction(
      IResources resources,
      IMultiSelectionActionAddableListView<IMp3Track> trackListView,
      IMusicSelectionModel selectionModel) {
    super(trackListView, new BasicUi(resources).getRemoveIcon());
    this.trackListView = trackListView;
    this.selectionModel = selectionModel;
    setToolTipText(resources.getString("Music.Actions.DeleteSelection.Tooltip")); //$NON-NLS-1$
  }

  @Override
  protected void execute(Component parentComponent) {
    selectionModel.removeFromCurrentSelection(trackListView.getSelectedIndices());
  }
}