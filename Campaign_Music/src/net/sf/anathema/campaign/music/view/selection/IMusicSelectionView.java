package net.sf.anathema.campaign.music.view.selection;

import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.presenter.selection.player.IMusicPlayerView;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.IMultiSelectionActionAddableListView;

public interface IMusicSelectionView {

  public IMultiSelectionActionAddableListView<IMp3Track> getTrackListView();

  public IActionAddableListView<IMusicSelection> getSelectionListView();

  public ITrackDetailsView getTrackDetailsView();

  public IMusicPlayerView getPlayerView();
}