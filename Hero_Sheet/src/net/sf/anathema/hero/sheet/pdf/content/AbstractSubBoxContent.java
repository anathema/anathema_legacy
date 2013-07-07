package net.sf.anathema.hero.sheet.pdf.content;

import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractSubBoxContent extends AbstractSubContent implements SubBoxContent {

  protected AbstractSubBoxContent(Resources resources) {
    super(resources);
  }

  @Override
  public String getHeader() {
    return getResources().getString("Sheet.Header." + getHeaderKey());
  }

  public abstract String getHeaderKey();
}
