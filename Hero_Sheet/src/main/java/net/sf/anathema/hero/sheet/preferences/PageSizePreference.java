package net.sf.anathema.hero.sheet.preferences;

import net.sf.anathema.framework.reporting.pdf.PageSize;

public class PageSizePreference {

  public PageSize getPageSize() {
     return PageFormatPreferenceElement.loadPreference();
  }
}
