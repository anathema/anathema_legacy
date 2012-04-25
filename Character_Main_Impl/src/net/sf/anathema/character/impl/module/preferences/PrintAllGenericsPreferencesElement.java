package net.sf.anathema.character.impl.module.preferences;

import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.PRINT_ALL_GENERICS;
import net.sf.anathema.framework.module.preferences.AbstractCheckBoxPreferencesElement;
import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.lib.util.IIdentificate;

@PreferenceElement
public class PrintAllGenericsPreferencesElement extends AbstractCheckBoxPreferencesElement implements
    ICharacterPreferencesElement {

  private boolean printAllGenerics = CHARACTER_PREFERENCES.getBoolean(PRINT_ALL_GENERICS, false);

  @Override
  public void savePreferences() {
    CHARACTER_PREFERENCES.putBoolean(PRINT_ALL_GENERICS, printAllGenerics);
  }

  @Override
  protected boolean getBooleanParameter() {
    return printAllGenerics;
  }

  @Override
  protected String getLabelKey() {
    return "Character.Tools.Preferences.PrintAllGenerics"; //$NON-NLS-1$
  }

  @Override
  protected void resetValue() {
    printAllGenerics = CHARACTER_PREFERENCES.getBoolean(PRINT_ALL_GENERICS, false);
  }

  @Override
  protected void setValue(boolean value) {
    printAllGenerics = value;
  }

  @Override
  public IIdentificate getCategory() {
    return CHARACTER_CATEGORY;
  }
}