package net.sf.anathema.campaign.music.presenter;

import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationProperties;
import net.sf.anathema.lib.resources.IResources;

public class MusicCategorizationProperties extends AbstractMusicProperties implements IMusicCategorizationProperties {

  public MusicCategorizationProperties(IResources resources) {
    super(resources);
  }

  public String getThemesString() {
    return getString("Music.Labels.Categorization.Themes"); //$NON-NLS-1$
  }

  public String getEventsString() {
    return getString("Music.Labels.Categorization.Events"); //$NON-NLS-1$
  }

  public String getMoodsString() {
    return getString("Music.Labels.Categorization.Moods"); //$NON-NLS-1$
  }
}