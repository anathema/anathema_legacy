package net.sf.anathema.framework.module.preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.LOOK_AND_FEEL_PREFERENCE;

public class LookAndFeelPreferencesElement extends AbstractCheckBoxPreferencesElement {
  private boolean useMetal = SYSTEM_PREFERENCES.getBoolean(LOOK_AND_FEEL_PREFERENCE, false);

  public void savePreferences() {
    SYSTEM_PREFERENCES.putBoolean(LOOK_AND_FEEL_PREFERENCE, useMetal);
  }

  @Override
  protected boolean getBooleanParameter() {
    return useMetal;
  }

  @Override
  protected void setValue(boolean value) {
    useMetal = value;
  }

  @Override
  protected String getLabelKey() {
    return "AnathemaCore.Tools.Preferences.LookFeel"; //$NON-NLS-1$
  }

  @Override
  protected void resetValue() {
    useMetal = SYSTEM_PREFERENCES.getBoolean(LOOK_AND_FEEL_PREFERENCE, false);
  }
}