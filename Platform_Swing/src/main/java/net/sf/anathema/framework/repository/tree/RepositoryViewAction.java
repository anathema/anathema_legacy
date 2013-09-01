package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.dialog.userdialog.DefaultDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;

public class RepositoryViewAction implements Command {
  private final IApplicationModel model;
  private final Environment environment;

  public RepositoryViewAction(IApplicationModel model, Environment environment) {
    this.model = model;
    this.environment = environment;
  }

  @Override
  public void execute() {
    RepositoryBrowserDialogPage page = new RepositoryBrowserDialogPage(environment, model);
    DefaultDialogConfiguration<RepositoryBrowserDialogPage> dialogConfiguration = DefaultDialogConfiguration.createWithOkOnly(page);
    UserDialog userDialog = new UserDialog(SwingApplicationFrame.getParentComponent(), dialogConfiguration);
    userDialog.getDialog().setModal(true);
    userDialog.getDialog().setResizable(false);
    userDialog.show();
  }
}