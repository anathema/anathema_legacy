package net.sf.anathema.framework.presenter.action;

import java.awt.Component;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.sf.anathema.framework.AboutDialogPage;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaAboutAction extends SmartAction {

  private final IResources resources;

  public AnathemaAboutAction(IResources resources) {
    this.resources = resources;
    setName(resources.getString("Help.AboutDialog.Title")); //$NON-NLS-1$
  }

  @Override
  protected void execute(Component parentComponent) {
    AboutDialogPage page = new AboutDialogPage(resources);
    // todo NOW vom (08.04.2006) (sieroux): Hier war nur ein Okay-Button 
    UserDialog dialog = new UserDialog(parentComponent, page);
    dialog.show();
  }
}