package net.sf.anathema.hero.sheet.pdf.page.layout;

import net.sf.anathema.hero.sheet.pdf.page.PageConfiguration;

public class Body {

  public final PageConfiguration configuration;
  public final float contentHeight;

  public Body(PageConfiguration configuration) {
    this(configuration, configuration.getContentHeight());
  }

  public Body(PageConfiguration configuration, float contentHeight) {
    this.configuration = configuration;
    this.contentHeight = contentHeight;
  }
}
