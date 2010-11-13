package net.sf.anathema.campaign.music.presenter;

import net.sf.anathema.lib.resources.IResources;

public class MusicPlayerProperties extends AbstractMusicProperties implements IMusicPlayerProperties {

  public MusicPlayerProperties(IResources resources) {
    super(resources);
  }

  public String getPlayerBorderString() {
    return getString("Music.Borders.Player"); //$NON-NLS-1$
  }

  public String getTimeString() {
    return getString("Music.Labels.Player.Time"); //$NON-NLS-1$
  }
}