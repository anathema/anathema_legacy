package net.sf.anathema.character.reporting.pdf.layout;

import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;

public class Footer {

  private final PageConfiguration configuration;
  private final float contentHeight;

  public Footer(PageConfiguration configuration, float contentHeight) {
    this.configuration = configuration;
    this.contentHeight = contentHeight;
  }
}
