package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IAnathemaBootJob;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.REPOSITORY_PREFERENCE;
import static net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement.SYSTEM_PREFERENCES;

/**
 * Copies the preferred repository directory to a new preference
 * to allow users to run current versions of Anathema in parallel with 3.1.x versions.
 */
@BootJob
public class RepositoryPreferenceUpdater implements IAnathemaBootJob {
  private static final String OLD_REPOSITORY_PREFERENCE = "Repository"; //$NON-NLS-1$

  @Override
  public void run(IResources resources, IAnathemaModel model, IAnathemaView view) {
    if (hasNoPreferenceStored()) {
      String oldValue = getValueOfOldPreference();
      if (valueIsSet(oldValue)) {
        storeValueAsNewPreference(oldValue);
      }
    }
    deleteOldPreference();
  }

  private boolean hasNoPreferenceStored() {
    String currentValue = SYSTEM_PREFERENCES.get(REPOSITORY_PREFERENCE, null);
    return !valueIsSet(currentValue);
  }

  private String getValueOfOldPreference() {
    return SYSTEM_PREFERENCES.get(OLD_REPOSITORY_PREFERENCE, null);
  }

  private boolean valueIsSet(String oldValue) {
    return oldValue != null;
  }

  private void storeValueAsNewPreference(String value) {
    SYSTEM_PREFERENCES.put(REPOSITORY_PREFERENCE, value);
  }

  private void deleteOldPreference() {
    SYSTEM_PREFERENCES.remove(OLD_REPOSITORY_PREFERENCE);
  }
}