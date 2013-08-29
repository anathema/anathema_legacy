package net.sf.anathema.hero.sheet.preferences;

import net.sf.anathema.framework.preferences.elements.PreferenceModel;
import net.sf.anathema.framework.preferences.elements.RegisteredPreferenceModel;
import net.sf.anathema.framework.preferences.persistence.PreferenceKey;
import net.sf.anathema.framework.preferences.persistence.PreferencePto;
import net.sf.anathema.framework.preferences.persistence.PreferenceValue;

@RegisteredPreferenceModel
public class SheetPreferencesModel implements PreferenceModel {

  public static final PreferenceKey KEY = new PreferenceKey("hero.sheet.pagesize");

  @Override
  public void serializeTo(PreferencePto pto) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void initializeFrom(PreferencePto pto) {
    PreferenceValue value = pto.map.get(KEY);
  }
}
