package net.sf.anathema.framework.presenter.action.preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.SYSTEM_PREFERENCES_NODE;

import java.util.prefs.Preferences;

import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public interface IPreferencesElement {

  public static final Preferences SYSTEM_PREFERENCES = Preferences.userRoot().node(SYSTEM_PREFERENCES_NODE);
  public static final IIdentificate SYSTEM_CATEGORY = new Identificate("System"); //$NON-NLS-1$

  public void savePreferences();

  public boolean isDirty();

  public IIdentificate getCategory();

  public void reset();

  public void addCompoment(IGridDialogPanel panel, IResources resources);
}