package net.sf.anathema.character.equipment.character.preference;

import java.util.prefs.Preferences;

import static net.sf.anathema.character.equipment.character.preference.IEquipmentPreferencesConstants.EQUIPMENT_PREFERENCES_NODE;
import static net.sf.anathema.character.equipment.character.preference.IEquipmentPreferencesConstants.ENABLE_PERSONALIZATION;

public class AnathemaEquipmentPreferences implements IAnathemaEquipmentPreferences {
  private static final IAnathemaEquipmentPreferences instance = new AnathemaEquipmentPreferences(Preferences.userRoot()
      .node(EQUIPMENT_PREFERENCES_NODE));

  private final Preferences equipmentPreferences;

  public AnathemaEquipmentPreferences(Preferences rootPreference) {
    this.equipmentPreferences = rootPreference;
  }

  public static IAnathemaEquipmentPreferences getDefaultPreferences() {
    return instance;
  }

  @Override
  public boolean getEnablePersonalization() {
    return equipmentPreferences.getBoolean(ENABLE_PERSONALIZATION, false);
  }
}