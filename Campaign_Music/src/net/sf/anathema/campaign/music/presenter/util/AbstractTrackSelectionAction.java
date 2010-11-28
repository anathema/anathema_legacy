package net.sf.anathema.campaign.music.presenter.util;

import javax.swing.Icon;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;

public abstract class AbstractTrackSelectionAction extends AbstractListViewSelectionEnabledAction<IMp3Track> {

  public AbstractTrackSelectionAction(IActionAddableListView<IMp3Track> view, Icon icon) {
    super(icon, view);
  }

  protected IMp3Track[] getSelectedTracks() {
    Object[] tracks = getSelectedItems();
    IMp3Track[] targetArray = new IMp3Track[tracks.length];
    System.arraycopy(tracks, 0, targetArray, 0, tracks.length);
    return targetArray;
  }
}