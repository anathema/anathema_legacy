package net.sf.anathema.framework.presenter.action.preferences;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.JOptionPane;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaPreferencesAction extends SmartAction {

  public static Action createMenuAction(IResources resources, IPreferencesElement[] elements) {
    SmartAction action = new AnathemaPreferencesAction(resources, elements);
    action.setName(resources.getString("AnathemaCore.Tools.Preferences.Name") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$
    return action;
  }

  private final IResources resources;
  private final IPreferencesElement[] elements;

  public AnathemaPreferencesAction(IResources resources, IPreferencesElement[] elements) {
    this.resources = resources;
    this.elements = elements;
  }

  @Override
  protected void execute(Component parentComponent) {
    AnathemaPreferencesPage page = new AnathemaPreferencesPage(resources, elements);
    UserDialog userDialog = new UserDialog(parentComponent, page);
    userDialog.show();
    if (userDialog.isCanceled()) {
      for (IPreferencesElement element : elements) {
        if (element.isDirty()) {
          element.reset();
        }
      }
      return;
    }
    boolean dirty = false;
    for (IPreferencesElement element : elements) {
      if (element.isDirty()) {
        element.savePreferences();
        dirty = true;
      }
    }
    if (dirty) {
      JOptionPane.showMessageDialog(parentComponent, resources.getString("AnathemaCore.Tools.Preferences.Restart")); //$NON-NLS-1$
    }
  }
}