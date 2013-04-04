package net.sf.anathema.character.equipment.impl.character.preferences;

import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

import java.util.prefs.Preferences;

import static net.sf.anathema.character.equipment.character.preference.IEquipmentPreferencesConstants.EQUIPMENT_PREFERENCES_NODE;

public interface IEquipmentPreferencesElement extends IPreferencesElement {

  Preferences EQUIPMENT_PREFERENCES = Preferences.userRoot().node(EQUIPMENT_PREFERENCES_NODE);
  Identified EQUIPMENT_CATEGORY = new Identifier("Equipment");
}