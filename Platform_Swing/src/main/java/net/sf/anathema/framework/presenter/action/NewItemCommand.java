package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.ItemReceiver;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.swing.MessageUtilities;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.core.ISwingFrameOrDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.wizard.selection.DialogBasedTemplateFactory;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

import javax.swing.JComponent;
import java.awt.Component;

public class NewItemCommand implements Command {

  private final ItemCreationOperator itemCreationOperator;
  private IApplicationModel model;
  private Resources resources;
  private final IItemType type;
  private final JComponent parent;

  public NewItemCommand(IItemType type, IApplicationModel model, Resources resources, ItemReceiver itemReceiver) {
    this.model = model;
    this.resources = resources;
    this.itemCreationOperator = new ItemCreationOperator(new NewItemCreator(model), itemReceiver);
    this.type = type;
    this.parent = SwingApplicationFrame.getParentComponent();
  }

  @Override
  public void execute() {
    IRegistry<String, IAnathemaExtension> registry = model.getExtensionPointRegistry();
    ItemTypeCreationViewPropertiesExtensionPoint extension =
            (ItemTypeCreationViewPropertiesExtensionPoint) registry.get(ItemTypeCreationViewPropertiesExtensionPoint.ID);
    DialogBasedTemplateFactory factory = extension.get(type).getNewItemWizardFactory();
    IDialogModelTemplate template = factory.createTemplate();
    if (factory.needsFurtherDetails()) {
      IDialogPage page = factory.createPage(template);
      boolean canceled = showDialog(parent, page);
      if (canceled) {
        return;
      }
    }
    try {
      itemCreationOperator.operate(parent, type, template);
    } catch (PersistenceException e) {
      Message message = new Message(resources.getString("AnathemaPersistence.NewMenu.Message.Error"), e);
      MessageUtilities.indicateMessage(NewItemCommand.class, parent, message);
    }
  }

  private boolean showDialog(Component parentComponent, IDialogPage page) {
    UserDialog dialog = new UserDialog(parentComponent, page);
    ISwingFrameOrDialog configuredDialog = dialog.getDialog();
    configuredDialog.setResizable(false);
    GuiUtilities.centerToParent(configuredDialog.getWindow());
    DialogResult result = dialog.show();
    return result.isCanceled();
  }
}
