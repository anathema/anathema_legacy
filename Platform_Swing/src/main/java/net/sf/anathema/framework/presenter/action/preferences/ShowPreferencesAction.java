package net.sf.anathema.framework.presenter.action.preferences;

import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JOptionPane;

public class ShowPreferencesAction implements Command {

  private final Resources resources;
  private final IPreferencesElement[] elements;

  public ShowPreferencesAction(Resources resources, IPreferencesElement[] elements) {
    this.resources = resources;
    this.elements = elements;
  }


  @Override
  public void execute() {
    PreferencesPage page = new PreferencesPage(resources, elements);
    UserDialog userDialog = new UserDialog(SwingApplicationFrame.getParentComponent(), page);

    boolean confirmed = false;
    boolean dirty = false;
    while (!confirmed) {
      DialogResult result = userDialog.show();
      confirmed = true;

      if (result.isCanceled()) {
        for (IPreferencesElement element : elements) {
          if (element.isDirty()) {
            element.reset();
          }
        }
      } else {
        dirty = false;
        for (IPreferencesElement element : elements) {
          if (element.isDirty()) {
            if (element.isValid()) {
              dirty = true;
            } else {
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
      JOptionPane.showMessageDialog(SwingApplicationFrame.getParentComponent(), resources.getString("AnathemaCore.Tools.Preferences.Restart"));
    }
  }
}