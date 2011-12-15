package net.sf.anathema.framework.module.preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.LOOK_AND_FEEL_PREFERENCE;
import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.USER_LOOK_AND_FEEL_CLASSNAME;
import static net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement.SYSTEM_PREFERENCES;

import javax.swing.plaf.metal.MetalLookAndFeel;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.IAnathemaBootJob;
import net.sf.anathema.lib.resources.IResources;

/**
 * Removes the "ForceMetalLookAndFeel" preference and sets the "UserLookAndFeel" preference
 * to "javax.swing.plaf.metal.MetalLookAndFeel" if "ForceMetalLookAndFeel" was set to {@code true}.
 * <P>
 * Note although it is not possible to set both preferences using Anathema if they were set
 * nevertheless by other means "UserLookAndFeel" will not be update regardless the value of
 * "ForceMetalLookAndFeel" (it will still remove "ForceMetalLookAndFeel").
 */
public class LookAndFeelNormalizerBootJob implements IAnathemaBootJob {
  @Override
  public void run(IResources resources, IAnathemaModel model, IAnathemaView view) {
    String storedClassName = SYSTEM_PREFERENCES.get(USER_LOOK_AND_FEEL_CLASSNAME, null);
    if (storedClassName == null) {
      if (SYSTEM_PREFERENCES.getBoolean(LOOK_AND_FEEL_PREFERENCE, false)) {
        SYSTEM_PREFERENCES.put(USER_LOOK_AND_FEEL_CLASSNAME, MetalLookAndFeel.class.getName());
      }
    }
    SYSTEM_PREFERENCES.remove(LOOK_AND_FEEL_PREFERENCE);
  }
}
