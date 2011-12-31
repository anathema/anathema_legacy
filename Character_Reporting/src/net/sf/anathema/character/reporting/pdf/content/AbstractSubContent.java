package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractSubContent implements SubContent {

  private IResources resources;

  protected AbstractSubContent(IResources resources) {
    this.resources = resources;
  }
  
  protected String getStringFromResource(String key, Object... args) {
    return resources.getString(key, args);
  }

  protected IResources getResources() {
    return resources;
  }

  @Override
  public boolean hasContent() {
    return true;
  }

  @Override
  public String getHeader() {
    return getString("Sheet.Header." + getHeaderKey());
  }

  protected String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }

  protected String getString(String resourceKey, Object... args) {
    return resources.getString(resourceKey, args);
  }
}
