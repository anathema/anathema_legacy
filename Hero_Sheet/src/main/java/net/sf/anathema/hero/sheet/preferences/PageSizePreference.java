package net.sf.anathema.hero.sheet.preferences;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.reporting.pdf.PageSize;

public class PageSizePreference {

  private Environment environment;

  public PageSizePreference(Environment environment) {
    this.environment = environment;
  }

  public PageSize getPageSize() {
    return PageFormatPreferenceElement.loadPreference();
  }
}
