package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.ItemReceiver;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.dialog.core.IDialogResult;
import net.sf.anathema.lib.gui.dialog.core.ISwingFrameOrDialog;
import net.sf.anathema.lib.gui.dialog.wizard.WizardDialog;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

import javax.swing.JComponent;
import java.awt.Component;

public class NewItemCommand implements Command {

  private final ItemCreationOperator itemCreationOperator;
  private IApplicationModel model;
  private IResources resources;
  private final IItemType type;
  private final JComponent parent;

  public NewItemCommand(IItemType type, IApplicationModel model, IResources resources, ItemReceiver itemReceiver) {
    this.model = model;
    this.resources = resources;
    this.itemCreationOperator = new ItemCreationOperator(new NewItemCreator(model), resources, itemReceiver, model);
    this.type = type;
    this.parent = SwingApplicationFrame.getParentComponent();
  }

  @Override
  public void execute() {
    IRegistry<String, IAnathemaExtension> registry = model.getExtensionPointRegistry();
    ItemTypeCreationViewPropertiesExtensionPoint extension =
            (ItemTypeCreationViewPropertiesExtensionPoint) registry.get(ItemTypeCreationViewPropertiesExtensionPoint.ID);
    IWizardFactory factory = extension.get(type).getNewItemWizardFactory();
    IAnathemaWizardModelTemplate template = factory.createTemplate();
    if (factory.needsFurtherDetails()) {
      IAnathemaWizardPage startPage = factory.createPage(template);
      boolean canceled = showDialog(parent, startPage);
      if (canceled) {
        return;
      }
    }
    try {
      itemCreationOperator.operate(parent, type, template);
    } catch (PersistenceException e) {
      Message message = new Message(resources.getString("AnathemaPersistence.NewMenu.Message.Error"), e); //$NON-NLS-1$
      MessageUtilities.indicateMessage(NewItemCommand.class, parent, message);
    }
  }

  private boolean showDialog(Component parentComponent, IAnathemaWizardPage startPage) {
    WizardDialog dialog = new AnathemaWizardDialog(parentComponent, startPage);
    ISwingFrameOrDialog configuredDialog = dialog.getConfiguredDialog();
    configuredDialog.setResizable(false);
    GuiUtilities.centerToParent(configuredDialog.getWindow());
    IDialogResult result = dialog.show();
    return result.isCanceled();
  }
}
