package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IAnathemaBootJob;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.*;

/**
 * Removes the Look and Feel settings if the "overrideLookAndFeel" system property
 * is set to "true". This boot job will also revert the current Look and Feel to the
 * system Look And Feel. This may introduce visual artifacts since the Look and Feel
 * was already set when boot jobs are being executed. However this is intended to be
 * an emergency task when an invalid Look and Feel makes Anathema unusable, so the
 * user should only set this property temporary.
 * <p/>
 * This is also known to cause problems when it actually changes the Look and Feel but
 * there is nothing to about it. The user can always restart Anathema and remove the above
 * mentioned property. Starting Anathema with this "recovery" property will remove the
 * previously set probably corrupted Look and Feel settings and this is the actual goal
 * of this task.
 */
@BootJob
public class LookAndFeelOverrideBootjob implements IAnathemaBootJob {
  @Override
  public void run(IResources resources, IAnathemaModel model, IAnathemaView view) {
    if (userRequestedLookAndFeelOverride()) {
      resetLookAndFeelPreference();
      if (currentLookAndFeelIsNotSystemLookAndFeel()) {
        setSystemLookAndFeel();
      }
    }
  }

  private boolean userRequestedLookAndFeelOverride() {
    return System.getProperty("overrideLookAndFeel", "false").equals("true");
  }

  private void resetLookAndFeelPreference() {
    IPreferencesElement.SYSTEM_PREFERENCES.remove(IAnathemaPreferencesConstants.USER_LOOK_AND_FEEL_CLASSNAME);
  }

  private boolean currentLookAndFeelIsNotSystemLookAndFeel() {
    String currentName = getCurrentLookAndFeel();
    String systemName = getSystemLookAndFeel();
    return systemName == null || !systemName.equals(currentName);
  }

  private void setSystemLookAndFeel() {
    String systemName = getSystemLookAndFeel();
    try {
      UIManager.setLookAndFeel(systemName);
    } catch (Exception ex) {
      Logger.getLogger(LookAndFeelOverrideBootjob.class).error(
              "Failed to revert to Look and Feel to the system Look and Feel", ex);
    }
  }

  private String getSystemLookAndFeel() {
    return UIManager.getSystemLookAndFeelClassName();
  }

  private String getCurrentLookAndFeel() {
    return UIManager.getLookAndFeel().getClass().getName();
  }
}