package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.framework.reporting.pdf.PageSize;

public class PageSizePreference {

  public PageSize getPageSize() {
     return PageFormatPreferenceElement.loadPreference();
  }
}
