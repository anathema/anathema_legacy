package net.sf.anathema.character.platform.module.preferences;

import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.prefs.Preferences;

import static net.sf.anathema.character.main.framework.configuration.ICharacterPreferencesConstants.CHARACTER_PREFERENCES_NODE;

public interface ICharacterPreferencesElement extends IPreferencesElement {

  Preferences CHARACTER_PREFERENCES = Preferences.userRoot().node(CHARACTER_PREFERENCES_NODE);
  Identifier CHARACTER_CATEGORY = new SimpleIdentifier("Hero");
}