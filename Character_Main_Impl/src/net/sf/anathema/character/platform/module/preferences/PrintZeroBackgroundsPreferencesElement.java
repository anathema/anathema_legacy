package net.sf.anathema.character.platform.module.preferences;

import net.sf.anathema.framework.module.preferences.AbstractCheckBoxPreferencesElement;
import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.util.Identified;

import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.PRINT_ZERO_BACKGROUNDS;

@PreferenceElement
@Weight(weight = 100)
public class PrintZeroBackgroundsPreferencesElement extends AbstractCheckBoxPreferencesElement implements ICharacterPreferencesElement {

  private boolean printZeroBackgrounds = CHARACTER_PREFERENCES.getBoolean(PRINT_ZERO_BACKGROUNDS, true);

  @Override
  public void savePreferences() {
    CHARACTER_PREFERENCES.putBoolean(PRINT_ZERO_BACKGROUNDS, printZeroBackgrounds);
  }

  @Override
  protected boolean getBooleanParameter() {
    return printZeroBackgrounds;
  }

  @Override
  protected String getLabelKey() {
    return "Character.Tools.Preferences.PrintZeroBackgrounds";
  }

  @Override
  protected void resetValue() {
    printZeroBackgrounds = CHARACTER_PREFERENCES.getBoolean(PRINT_ZERO_BACKGROUNDS, true);
  }

  @Override
  protected void setValue(boolean value) {
    printZeroBackgrounds = value;
  }

  @Override
  public Identified getCategory() {
    return CHARACTER_CATEGORY;
  }
}