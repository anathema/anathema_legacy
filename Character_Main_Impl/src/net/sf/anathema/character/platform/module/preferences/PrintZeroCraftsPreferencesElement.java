package net.sf.anathema.character.platform.module.preferences;

import net.sf.anathema.framework.module.preferences.AbstractCheckBoxPreferencesElement;
import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.util.Identified;

import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.PRINT_ZERO_CRAFTS;

@PreferenceElement
@Weight(weight = 110)
public class PrintZeroCraftsPreferencesElement extends AbstractCheckBoxPreferencesElement implements ICharacterPreferencesElement {

  private boolean printZeroCrafts = CHARACTER_PREFERENCES.getBoolean(PRINT_ZERO_CRAFTS, true);

  @Override
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

  @Override
  public Identified getCategory() {
    return CHARACTER_CATEGORY;
  }
}