package net.sf.anathema.campaign.music.presenter.selection;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.presenter.util.AbstractTrackSelectionAction;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Component;

public class DeleteSelectionTracksAction extends AbstractTrackSelectionAction {
  private final Runnable closure;

  public DeleteSelectionTracksAction(IResources resources, IActionAddableListView<IMp3Track> view, Runnable closure) {
    super(view, new BasicUi(resources).getRemoveIcon());
    this.closure = closure;
    setToolTipText(resources.getString("Music.Actions.DeleteSelection.Tooltip")); //$NON-NLS-1$
  }

  @Override
  protected void execute(Component parentComponent) {
    closure.run();
  }
}