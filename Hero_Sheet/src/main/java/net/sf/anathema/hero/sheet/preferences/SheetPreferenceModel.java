package net.sf.anathema.hero.sheet.preferences;

import net.sf.anathema.framework.preferences.elements.PreferenceModel;
import net.sf.anathema.framework.preferences.elements.RegisteredPreferenceModel;
import net.sf.anathema.framework.preferences.persistence.PreferenceKey;
import net.sf.anathema.framework.preferences.persistence.PreferencePto;
import net.sf.anathema.framework.preferences.persistence.PreferenceValue;
import net.sf.anathema.framework.reporting.pdf.PageSize;

@RegisteredPreferenceModel
public class SheetPreferenceModel implements PreferenceModel {

  public static final PreferenceKey KEY = new PreferenceKey("hero.sheet.pagesize");
  private PageSize pageSize;

  @Override
  public void serializeTo(PreferencePto pto) {
    PreferenceValue value = new PreferenceValue(pageSize.name());
    pto.map.put(KEY, value);
  }

  @Override
  public void initializeFrom(PreferencePto pto) {
    PreferenceValue value = pto.map.get(KEY);
    if (value == null) {
      this.pageSize = PageSize.A4;
      return;
    }
    this.pageSize = PageSize.valueOf(value.value);
  }
}