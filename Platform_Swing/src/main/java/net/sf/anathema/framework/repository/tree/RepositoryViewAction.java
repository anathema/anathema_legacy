package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.dialog.userdialog.DefaultDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.Resources;

public class RepositoryViewAction implements Command {
  private final IApplicationModel model;
  private final Resources resources;

  public RepositoryViewAction(IApplicationModel model, Resources resources) {
    this.model = model;
    this.resources = resources;
  }

  @Override
  public void execute() {
    RepositoryBrowserDialogPage page = new RepositoryBrowserDialogPage(resources, model);
    DefaultDialogConfiguration<RepositoryBrowserDialogPage> dialogConfiguration = DefaultDialogConfiguration.createWithOkOnly(page);
    UserDialog userDialog = new UserDialog(SwingApplicationFrame.getParentComponent(), dialogConfiguration);
    userDialog.getDialog().setModal(true);
    userDialog.getDialog().setResizable(false);
    userDialog.show();
  }
}