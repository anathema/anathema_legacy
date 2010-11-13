package net.sf.anathema.campaign.music.presenter;

import net.sf.anathema.lib.resources.IResources;

public class MusicSelectionProperties extends AbstractMusicProperties implements IMusicSelectionProperties {

  public MusicSelectionProperties(IResources resources) {
    super(resources);
  }

  public String getSavedSelectionsTitle() {
    return getString("Music.Labels.SelectionsList.Title"); //$NON-NLS-1$
  }

  public String getMusicSelectionBorderTitle() {
    return getString("Music.Borders.MusicSelection"); //$NON-NLS-1$
  }

  public String getSelectionsString() {
    return getString("Music.Tabs.Selections"); //$NON-NLS-1$
  }

  public String getTrackDetailsString() {
    return getString("Music.Tabs.TrackDetails"); //$NON-NLS-1$
  }

  public String getNoDecoderString() {
    return getString("Music.Labels.Player.NoDecoder"); //$NON-NLS-1$
  }

  public String getCurrentSelectionString() {
    return getString("Music.Labels.CurrentSelection.Title"); //$NON-NLS-1$
  }

  public String getCurrentlySelectedTracksString() {
    return getString("Music.Labels.CurrentSelection.Title"); //$NON-NLS-1$
  }
}