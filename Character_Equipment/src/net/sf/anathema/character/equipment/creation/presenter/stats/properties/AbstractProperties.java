package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.lib.resources.Resources;

public class AbstractProperties {
  private final Resources resources;

  public AbstractProperties(Resources resources) {
    this.resources = resources;
  }

  protected final String getString(String key) {
    return resources.getString(key);
  }

  protected final String getLabelString(String key) {
    return getString(key) + ":"; //$NON-NLS-1$
  }

  protected final Resources getResources() {
    return resources;
  }
}
