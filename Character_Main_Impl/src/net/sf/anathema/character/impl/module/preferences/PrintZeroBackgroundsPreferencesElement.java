package net.sf.anathema.character.impl.module.preferences;

import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.PRINT_ZERO_BACKGROUNDS;
import net.sf.anathema.framework.module.preferences.AbstractCheckBoxPreferencesElement;
import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.lib.util.IIdentificate;

@PreferenceElement
public class PrintZeroBackgroundsPreferencesElement extends AbstractCheckBoxPreferencesElement implements
    ICharacterPreferencesElement {

  private boolean printZeroBackgrounds = CHARACTER_PREFERENCES.getBoolean(PRINT_ZERO_BACKGROUNDS, true);

  public void savePreferences() {
    CHARACTER_PREFERENCES.putBoolean(PRINT_ZERO_BACKGROUNDS, printZeroBackgrounds);
  }

  @Override
  protected boolean getBooleanParameter() {
    return printZeroBackgrounds;
  }

  @Override
  protected String getLabelKey() {
    return "Character.Tools.Preferences.PrintZeroBackgrounds"; //$NON-NLS-1$
  }

  @Override
  protected void resetValue() {
    printZeroBackgrounds = CHARACTER_PREFERENCES.getBoolean(PRINT_ZERO_BACKGROUNDS, true);
  }

  @Override
  protected void setValue(boolean value) {
    printZeroBackgrounds = value;
  }

  public IIdentificate getCategory() {
    return CHARACTER_CATEGORY;
  }
}