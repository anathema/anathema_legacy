package net.sf.anathema.charmentry.module;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.Action;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.core.ISwingFrameOrDialog;
import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.disy.commons.swing.util.GuiUtilities;
import net.sf.anathema.charmentry.model.WizardCharmEntryModel;
import net.sf.anathema.charmentry.presenter.HeaderDataEntryPage;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.resources.IResources;

public class ShowCharmEntryAction extends SmartAction {

  private final IResources resources;

  public static Action createMenuAction(IResources resources) {
    SmartAction action = new ShowCharmEntryAction(resources.getString("CharmEntry.Show.Name"), resources); //$NON-NLS-1$
    return action;
  }

  public ShowCharmEntryAction(String string, IResources resources) {
    this.resources = resources;
    setName(string);
  }

  @Override
  protected void execute(Component parentComponent) {
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try {
      ICharmEntryModel model = new WizardCharmEntryModel();
      ICharmEntryViewFactory viewFactory = new CharmEntryViewFactory(resources);
      HeaderDataEntryPage startPage = new HeaderDataEntryPage(resources, model, viewFactory);
      WizardDialog dialog = new AnathemaWizardDialog(parentComponent, startPage);
      final ISwingFrameOrDialog configuredDialog = dialog.getConfiguredDialog();
      configuredDialog.setModal(true);
      configuredDialog.setResizable(false);
      GuiUtilities.centerToParent(configuredDialog.getWindow());
      configuredDialog.show();
    }
    finally {
      parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }
}