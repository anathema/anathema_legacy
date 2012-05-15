package net.sf.anathema.campaign.music.view.selection;

import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ITrackDetailsView {

  ITextView getGivenNameView();

  IMusicCategorizationView getMusicCategorizationView();

  void setAlbumTitle(String album);

  void setArtistName(String artist);

  void setOriginalTitle(String title);

  void setTrackNumber(String track);

  void showTrackInfo(boolean show);
}