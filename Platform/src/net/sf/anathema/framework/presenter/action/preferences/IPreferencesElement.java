package net.sf.anathema.framework.presenter.action.preferences;

import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

import javax.swing.JPanel;
import java.util.prefs.Preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.SYSTEM_PREFERENCES_NODE;

public interface IPreferencesElement {

  Preferences SYSTEM_PREFERENCES = Preferences.userRoot().node(SYSTEM_PREFERENCES_NODE);
  Identified SYSTEM_CATEGORY = new Identifier("System");

  void savePreferences();
  
  boolean isValid();

  boolean isDirty();

  Identified getCategory();

  void reset();

  void addComponent(JPanel panel, Resources resources);
}
