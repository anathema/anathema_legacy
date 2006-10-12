package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.lib.resources.IResources;

public class AbstractProperties {
  private final IResources resources;

  public AbstractProperties(IResources resources) {
    this.resources = resources;
  }

  protected final String getString(String key) {
    return resources.getString(key);
  }

  protected final String getLabelString(String key) {
    return getString(key) + ":"; //$NON-NLS-1$
  }

  protected final IResources getResources() {
    return resources;
  }
}
