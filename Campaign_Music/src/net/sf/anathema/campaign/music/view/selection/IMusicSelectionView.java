package net.sf.anathema.campaign.music.view.selection;

import net.sf.anathema.campaign.music.presenter.selection.player.IMusicPlayerView;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.IMultiSelectionActionAddableListView;

public interface IMusicSelectionView {

  public IMultiSelectionActionAddableListView getTrackListView();

  public IActionAddableListView getSelectionListView();

  public ITrackDetailsView getTrackDetailsView();

  public IMusicPlayerView getPlayerView();
}