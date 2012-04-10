package net.sf.anathema.character.equipment.impl.character.preferences;

import java.util.prefs.Preferences;

import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import static net.sf.anathema.character.equipment.character.preference.IEquipmentPreferencesConstants.EQUIPMENT_PREFERENCES_NODE;

public interface IEquipmentPreferencesElement extends IPreferencesElement {

  public static final Preferences EQUIPMENT_PREFERENCES = Preferences.userRoot().node(EQUIPMENT_PREFERENCES_NODE);
  public static final IIdentificate EQUIPMENT_CATEGORY = new Identificate("Equipment"); //$NON-NLS-1$
}