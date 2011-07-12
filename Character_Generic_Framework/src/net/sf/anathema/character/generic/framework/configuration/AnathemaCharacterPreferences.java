package net.sf.anathema.character.generic.framework.configuration;

import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.CHARACTER_PREFERENCES_NODE;
import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.DEFAULT_RULESET;
import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.RULESET_PREFERENCE;
import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.PRINT_ZERO_BACKGROUNDS;
import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.PRINT_ZERO_CRAFTS;
import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.PRINT_ZERO_INTIMACIES;

import java.util.prefs.Preferences;

import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class AnathemaCharacterPreferences implements IAnathemaCharacterPreferences {
  private static final IAnathemaCharacterPreferences instance = new AnathemaCharacterPreferences(Preferences.userRoot()
      .node(CHARACTER_PREFERENCES_NODE));

  private final Preferences characterPreferences;

  public AnathemaCharacterPreferences(Preferences rootPreference) {
    this.characterPreferences = rootPreference;
  }

  public static IAnathemaCharacterPreferences getDefaultPreferences() {
    return instance;
  }

  public IExaltedRuleSet getPreferredRuleset() {
    return ExaltedRuleSet.valueOf(characterPreferences.get(RULESET_PREFERENCE, DEFAULT_RULESET));
  }

  public boolean printZeroBackgrounds() {
    return characterPreferences.getBoolean(PRINT_ZERO_BACKGROUNDS, true);
  }

  public boolean printZeroCrafts() {
    return characterPreferences.getBoolean(PRINT_ZERO_CRAFTS, true);
  }

  public boolean printZeroIntimacies() {
    return characterPreferences.getBoolean(PRINT_ZERO_INTIMACIES, true);
  }
}