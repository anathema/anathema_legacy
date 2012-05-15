package net.sf.anathema.framework.presenter.action.preferences;

import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.prefs.Preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.SYSTEM_PREFERENCES_NODE;

public interface IPreferencesElement {

  Preferences SYSTEM_PREFERENCES = Preferences.userRoot().node(SYSTEM_PREFERENCES_NODE);
  IIdentificate SYSTEM_CATEGORY = new Identificate("System"); //$NON-NLS-1$

  void savePreferences();
  
  boolean isValid();

  boolean isDirty();

  IIdentificate getCategory();

  void reset();

  void addComponent(IGridDialogPanel panel, IResources resources);
}
