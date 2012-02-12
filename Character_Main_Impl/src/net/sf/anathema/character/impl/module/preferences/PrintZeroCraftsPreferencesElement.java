package net.sf.anathema.character.impl.module.preferences;

import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.PRINT_ZERO_CRAFTS;
import net.sf.anathema.framework.module.preferences.AbstractCheckBoxPreferencesElement;
import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.lib.util.IIdentificate;

@PreferenceElement
public class PrintZeroCraftsPreferencesElement extends AbstractCheckBoxPreferencesElement implements
    ICharacterPreferencesElement {

  private boolean printZeroCrafts = CHARACTER_PREFERENCES.getBoolean(PRINT_ZERO_CRAFTS, true);

  public void savePreferences() {
    CHARACTER_PREFERENCES.putBoolean(PRINT_ZERO_CRAFTS, printZeroCrafts);
  }

  @Override
  protected boolean getBooleanParameter() {
    return printZeroCrafts;
  }

  @Override
  protected String getLabelKey() {
    return "Character.Tools.Preferences.PrintZeroCrafts"; //$NON-NLS-1$
  }

  @Override
  protected void resetValue() {
    printZeroCrafts = CHARACTER_PREFERENCES.getBoolean(PRINT_ZERO_CRAFTS, true);
  }

  @Override
  protected void setValue(boolean value) {
    printZeroCrafts = value;
  }

  public IIdentificate getCategory() {
    return CHARACTER_CATEGORY;
  }
}