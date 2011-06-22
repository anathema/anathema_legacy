package net.sf.anathema.framework.presenter.itemmanagement;

import java.awt.Component;
import java.awt.Cursor;

import net.disy.commons.core.message.IMessage;
import net.disy.commons.core.message.Message;
import net.disy.commons.core.message.MessageType;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.message.MessageUserDialogConfiguration;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.disy.commons.swing.dialog.userdialog.buttons.AbstractDialogButtonConfiguration;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.SelectedItemActionEnabler;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.dialog.YesNoDialogButtonConfiguration;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractAnathemaCloseAction extends SmartAction {

  private static final long serialVersionUID = 231060390775642729L;
  private final IItemMangementModel management;
  private final IResources resources;

  public AbstractAnathemaCloseAction(IItemMangementModel management, IResources resources) {
    this.management = management;
    this.resources = resources;
    management.addListener(new SelectedItemActionEnabler(this, management.getSelectedItem()));
  }

  protected final IItemMangementModel getManagement() {
    return management;
  }

  protected abstract IItem getItemToClose();

  @Override
  protected final void execute(Component parentComponent) {
    IItem selectedItem = getItemToClose();
    if (selectedItem == null) {
      return;
    }
    if (selectedItem.isDirty()) {
      String messageText = resources.getString("AnathemaCore.Tools.Close.DirtyQuestion"); //$NON-NLS-1$
      IMessage message = new Message(messageText, MessageType.WARNING);
      AbstractDialogButtonConfiguration buttonConfiguration = new YesNoDialogButtonConfiguration(resources);
      MessageUserDialogConfiguration configuration = new MessageUserDialogConfiguration(message, buttonConfiguration);
      UserDialog userDialog = new UserDialog(parentComponent, configuration);
      userDialog.show();
      if (userDialog.isCanceled()) {
        return;
      }
    }
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    management.removeItem(selectedItem);
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }
}