package net.sf.anathema.character.platform.module.preferences;

import net.sf.anathema.framework.module.preferences.AbstractCheckBoxPreferencesElement;
import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.util.Identifier;

import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.PRINT_ALL_GENERICS;

@PreferenceElement
@Weight(weight = 90)
public class PrintAllGenericsPreferencesElement extends AbstractCheckBoxPreferencesElement implements ICharacterPreferencesElement {

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
    return "Hero.Tools.Preferences.PrintAllGenerics";
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
  public Identifier getCategory() {
    return CHARACTER_CATEGORY;
  }
}