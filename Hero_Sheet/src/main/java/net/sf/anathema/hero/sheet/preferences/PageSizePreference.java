package net.sf.anathema.hero.sheet.preferences;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.preferences.persistence.PreferenceValue;
import net.sf.anathema.framework.reporting.pdf.PageSize;

public class PageSizePreference {

  private Environment environment;

  public PageSizePreference(Environment environment) {
    this.environment = environment;
  }

  public PageSize getPageSize() {
    String value = environment.getPreference(SheetPreferenceModel.KEY.key);
    PreferenceValue preferenceValue = new PreferenceValue(value);
    return SheetPreferenceModel.calculatePageSize(preferenceValue);
  }
}