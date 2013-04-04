package net.sf.anathema.framework.presenter.action.menu.help;

import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.userdialog.DefaultDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Action;
import java.awt.Component;

public class AnathemaAboutAction extends SmartAction {

  private final Resources resources;

  public static Action createMenuAction(Resources resources) {
    SmartAction action = new AnathemaAboutAction(resources);
    action.setName(resources.getString("Help.AboutDialog.Title"));
    return action;
  }

  private AnathemaAboutAction(Resources resources) {
    this.resources = resources;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void execute(Component parentComponent) {
    IDialogPage page = new AboutDialogPage(resources);
    DefaultDialogConfiguration<IDialogPage> dialogConfiguration = DefaultDialogConfiguration.createWithOkOnly(page);
    UserDialog dialog = new UserDialog(parentComponent, dialogConfiguration);
    dialog.show();
  }
}