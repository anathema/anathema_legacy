package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.util.Identifier;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.MAXIMIZE_PREFERENCE;

@PreferenceElement
@Weight(weight = 60)
public class MaximizePreferencesElement extends AbstractCheckBoxPreferencesElement {

  private boolean maximize = SYSTEM_PREFERENCES.getBoolean(MAXIMIZE_PREFERENCE, false);

  @Override
  protected boolean getBooleanParameter() {
    return maximize;
  }

  @Override
  protected void setValue(boolean value) {
    maximize = value;
  }

  @Override
  protected String getLabelKey() {
    return "AnathemaCore.Tools.Preferences.Maximize";
  }

  @Override
  public void savePreferences() {
    SYSTEM_PREFERENCES.putBoolean(MAXIMIZE_PREFERENCE, maximize);
  }

  @Override
  protected void resetValue() {
    maximize = SYSTEM_PREFERENCES.getBoolean(MAXIMIZE_PREFERENCE, false);
  }

  @Override
  public Identifier getCategory() {
    return SYSTEM_CATEGORY;
  }
}