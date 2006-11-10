package net.sf.anathema.campaign.music.view.selection;

import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ITrackDetailsView {

  public ITextView getGivenNameView();

  public IMusicCategorizationView getMusicCategorizationView();

  public void setAlbumTitle(String album);

  public void setArtistName(String artist);

  public void setOriginalTitle(String title);

  public void setTrackNumber(String track);

  public void showTrackInfo(boolean show);
}