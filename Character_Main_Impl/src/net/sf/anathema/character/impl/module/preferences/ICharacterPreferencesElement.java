package net.sf.anathema.character.impl.module.preferences;

import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.prefs.Preferences;

import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.CHARACTER_PREFERENCES_NODE;

public interface ICharacterPreferencesElement extends IPreferencesElement {

  Preferences CHARACTER_PREFERENCES = Preferences.userRoot().node(CHARACTER_PREFERENCES_NODE);
  IIdentificate CHARACTER_CATEGORY = new Identificate("Character"); //$NON-NLS-1$
}