package net.sf.anathema.character.reporting.pdf.layout;

import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;

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
