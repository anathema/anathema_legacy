package net.sf.anathema.hero.sheet.pdf.content;

import net.sf.anathema.framework.environment.Resources;

public abstract class AbstractSubContent implements SubContent {

  private Resources resources;

  protected AbstractSubContent(Resources resources) {
    this.resources = resources;
  }

  protected Resources getResources() {
    return resources;
  }

  @Override
  public boolean hasContent() {
    return true;
  }

  protected String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }

  protected String getString(String resourceKey, Object... args) {
    return resources.getString(resourceKey, args);
  }
}
