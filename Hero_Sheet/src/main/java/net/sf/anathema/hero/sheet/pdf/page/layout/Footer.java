package net.sf.anathema.hero.sheet.pdf.page.layout;

import net.sf.anathema.hero.sheet.pdf.page.PageConfiguration;

public class Footer {

  private final PageConfiguration configuration;
  private final float contentHeight;

  public Footer(PageConfiguration configuration, float contentHeight) {
    this.configuration = configuration;
    this.contentHeight = contentHeight;
  }
}
