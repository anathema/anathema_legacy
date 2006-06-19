package net.sf.anathema.campaign.music.presenter.selection;

import java.awt.Component;

import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.presenter.util.AbstractTrackSelectionAction;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

public class MoveSelectedTracksToSelectionAction extends AbstractTrackSelectionAction {

  private final IMusicSelectionModel selectionModel;

  public MoveSelectedTracksToSelectionAction(
      IResources resources,
      IActionAddableListView<IMp3Track> trackListView,
      IMusicSelectionModel selectionModel) {
    super(trackListView, new BasicUi(resources).getDoubleDownArrowIcon());
    setToolTipText(resources.getString("Music.Actions.MoveTracksToSelection.Tooltip")); //$NON-NLS-1$
    this.selectionModel = selectionModel;
  }

  @Override
  protected void execute(Component parentComponent) {
    selectionModel.addToCurrentSelection(getSelectedTracks());
  }
}