package net.sf.anathema.framework.presenter.action.preferences;

import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.core.IDialogResult;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Action;
import javax.swing.JOptionPane;
import java.awt.Component;

public class ShowPreferencesAction extends SmartAction {

  public static Action createMenuAction(Resources resources, IPreferencesElement[] elements) {
    SmartAction action = new ShowPreferencesAction(resources, elements);
    action.setName(resources.getString("AnathemaCore.Tools.Preferences.Name") + "\u2026");
    return action;
  }

  private final Resources resources;
  private final IPreferencesElement[] elements;

  public ShowPreferencesAction(Resources resources, IPreferencesElement[] elements) {
    this.resources = resources;
    this.elements = elements;
  }

  @Override
  protected void execute(Component parentComponent) {
    PreferencesPage page = new PreferencesPage(resources, elements);
    UserDialog userDialog = new UserDialog(parentComponent, page);
    
    boolean confirmed = false;
    boolean dirty = false;
    while (!confirmed) {
      IDialogResult result = userDialog.show();
      confirmed = true;
      
      if (result.isCanceled()) {
        for (IPreferencesElement element : elements) {
          if (element.isDirty()) {
            element.reset();
          }
        }
      }
      else {
        dirty = false;
        for (IPreferencesElement element : elements) {
          if (element.isDirty()) {
            if (element.isValid()) {
              dirty = true;
            }
            else {
              confirmed = false;
            }
          }
        }
      }
    }
    for (IPreferencesElement element : elements) {
      if (element.isDirty()) {
        element.savePreferences();
        dirty = true;
      }
    }
    if (dirty) {
      JOptionPane.showMessageDialog(parentComponent, resources.getString("AnathemaCore.Tools.Preferences.Restart"));
    }
  }
}