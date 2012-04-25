package net.sf.anathema.campaign.music.presenter;

import net.sf.anathema.lib.resources.IResources;

public class TrackDetailsProperties extends AbstractMusicProperties implements ITrackDetailsProperties {

  public TrackDetailsProperties(IResources resources) {
    super(resources);
  }

  @Override
  public String getNoContentString() {
    return getString("Music.Labels.TrackDetails.NoTrackSelected"); //$NON-NLS-1$
  }

  @Override
  public String getOriginalNameString() {
    return getString("Music.Labels.TrackDetails.TrackTitle"); //$NON-NLS-1$
  }

  @Override
  public String getInfoBorder() {
    return getString("Music.Borders.Info"); //$NON-NLS-1$
  }

  @Override
  public String getGivenNameLabel() {
    return getString("Music.Labels.TrackDetails.GivenName"); //$NON-NLS-1$
  }

  @Override
  public String getArtistLabel() {
    return getString("Music.Labels.TrackDetails.Artist"); //$NON-NLS-1$
  }

  @Override
  public String getTrackNumberLabel() {
    return getString("Music.Labels.TrackDetails.TrackNumber"); //$NON-NLS-1$
  }

  @Override
  public String getAlbumLabel() {
    return getString("Music.Labels.TrackDetails.AlbumTitle"); //$NON-NLS-1$
  }
}