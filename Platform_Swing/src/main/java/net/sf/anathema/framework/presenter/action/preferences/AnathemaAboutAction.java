package net.sf.anathema.framework.presenter.action.preferences;

import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.dialog.userdialog.DefaultDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.resources.Resources;

public class AnathemaAboutAction implements Command {

  private final Resources resources;

  public AnathemaAboutAction(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void execute() {
    IDialogPage page = new AboutDialogPage(resources);
    DefaultDialogConfiguration<IDialogPage> dialogConfiguration = DefaultDialogConfiguration.createWithOkOnly(page);
    UserDialog dialog = new UserDialog(SwingApplicationFrame.getParentComponent(), dialogConfiguration);
    dialog.show();
  }
}