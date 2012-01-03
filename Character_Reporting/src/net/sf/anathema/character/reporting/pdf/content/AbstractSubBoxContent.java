package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractSubBoxContent extends AbstractSubContent implements SubBoxContent {

  protected AbstractSubBoxContent(IResources resources) {
    super(resources);
  }

  @Override
  public String getHeader() {
    return getResources().getString("Sheet.Header." + getHeaderKey());
  }
}
