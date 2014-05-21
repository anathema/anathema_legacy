package net.sf.anathema.character.framework.configuration;

import java.util.prefs.Preferences;

public class AnathemaCharacterPreferences implements IAnathemaCharacterPreferences {
  private static final IAnathemaCharacterPreferences instance =
          new AnathemaCharacterPreferences(Preferences.userRoot().node(ICharacterPreferencesConstants.CHARACTER_PREFERENCES_NODE));

  private final Preferences characterPreferences;

  public AnathemaCharacterPreferences(Preferences rootPreference) {
    this.characterPreferences = rootPreference;
  }

  public static IAnathemaCharacterPreferences getDefaultPreferences() {
    return instance;
  }

  @Override
  public boolean printZeroBackgrounds() {
    return characterPreferences.getBoolean(ICharacterPreferencesConstants.PRINT_ZERO_BACKGROUNDS, true);
  }

  @Override
  public boolean printZeroCrafts() {
    return characterPreferences.getBoolean(ICharacterPreferencesConstants.PRINT_ZERO_CRAFTS, true);
  }

  @Override
  public boolean printZeroIntimacies() {
    return characterPreferences.getBoolean(ICharacterPreferencesConstants.PRINT_ZERO_INTIMACIES, true);
  }

  @Override
  public boolean printAllGenerics() {
    return characterPreferences.getBoolean(ICharacterPreferencesConstants.PRINT_ALL_GENERICS, false);
  }
}