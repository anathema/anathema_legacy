package net.sf.anathema.campaign.music.model;

import net.sf.anathema.campaign.music.model.libary.ILibraryControl;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.presenter.IMusicSearchControl;
import net.sf.anathema.campaign.music.presenter.selection.player.IMusicPlayerModel;
import net.sf.anathema.framework.itemdata.model.IItemData;

public interface IMusicDatabase extends IItemData {

  ILibraryControl getLibraryControl();

  IMusicSelectionModel getMusicSelectionModel();

  IMusicPlayerModel getMusicPlayerModel();

  IMusicSearchControl getMusicSearchControl();
}