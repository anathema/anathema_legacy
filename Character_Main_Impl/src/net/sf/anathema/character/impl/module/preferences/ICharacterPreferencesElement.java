package net.sf.anathema.character.impl.module.preferences;

import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.CHARACTER_PREFERENCES_NODE;

import java.util.prefs.Preferences;

import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public interface ICharacterPreferencesElement extends IPreferencesElement {

  public static final Preferences CHARACTER_PREFERENCES = Preferences.userRoot().node(CHARACTER_PREFERENCES_NODE);
  public static final IIdentificate CHARACTER_CATEGORY = new Identificate("Character"); //$NON-NLS-1$
}