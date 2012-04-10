package net.sf.anathema.character.equipment.impl.character.preferences;

import net.sf.anathema.framework.module.preferences.AbstractCheckBoxPreferencesElement;
import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.lib.util.IIdentificate;

import static net.sf.anathema.character.equipment.character.preference.IEquipmentPreferencesConstants.ENABLE_PERSONALIZATION;

@PreferenceElement
public class EnablePersonalizationPreferencesElement extends AbstractCheckBoxPreferencesElement implements
    IEquipmentPreferencesElement {

  private boolean enablePersonalization = EQUIPMENT_PREFERENCES.getBoolean(ENABLE_PERSONALIZATION, false);

  public void savePreferences() {
	  EQUIPMENT_PREFERENCES.putBoolean(ENABLE_PERSONALIZATION, enablePersonalization);
  }

  @Override
  protected boolean getBooleanParameter() {
    return enablePersonalization;
  }

  @Override
  protected String getLabelKey() {
    return "Equipment.Tools.Preferences.EnablePersonalization"; //$NON-NLS-1$
  }

  @Override
  protected void resetValue() {
    enablePersonalization = EQUIPMENT_PREFERENCES.getBoolean(ENABLE_PERSONALIZATION, false);
  }

  @Override
  protected void setValue(boolean value) {
    enablePersonalization = value;
  }

  public IIdentificate getCategory() {
    return EQUIPMENT_CATEGORY;
  }
}