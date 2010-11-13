package net.sf.anathema.campaign.music.presenter;

import net.sf.anathema.lib.resources.IResources;

public class AbstractMusicProperties {

  private final IResources resources;

  public AbstractMusicProperties(IResources resources) {
    this.resources = resources;
  }

  protected String getString(String key) {
    return resources.getString(key);
  }
}