package net.sf.anathema.framework.module.preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.MAXIMIZE_PREFERENCE;

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
    return "AnathemaCore.Tools.Preferences.Maximize"; //$NON-NLS-1$
  }

  public void savePreferences() {
    SYSTEM_PREFERENCES.putBoolean(MAXIMIZE_PREFERENCE, maximize);
  }

  @Override
  protected void resetValue() {
    maximize = SYSTEM_PREFERENCES.getBoolean(MAXIMIZE_PREFERENCE, false);
  }
}