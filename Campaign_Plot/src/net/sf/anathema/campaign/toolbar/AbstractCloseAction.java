package net.sf.anathema.campaign.toolbar;

import net.sf.anathema.campaign.item.PlotItemManagement;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.message.MessageUserDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.DialogButtonConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.YesNoDialogButtonConfiguration;
import net.sf.anathema.lib.message.IMessage;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.resources.Resources;

import java.awt.Component;
import java.awt.Cursor;

public abstract class AbstractCloseAction extends SmartAction {

  private final PlotItemManagement management;
  private final Resources resources;
  private final SelectedItemActionEnabler itemActionEnabler;

  public AbstractCloseAction(PlotItemManagement management, Resources resources) {
    this.management = management;
    this.resources = resources;
    this.itemActionEnabler = new SelectedItemActionEnabler(this, management.getSelectedItem());
    management.addListener(itemActionEnabler);
  }

  protected abstract IItem getItemToClose();

  @Override
  protected final void execute(Component parentComponent) {
    IItem selectedItem = getItemToClose();
    if (selectedItem == null) {
      return;
    }
    if (selectedItem.isDirty()) {
      String messageText = resources.getString("AnathemaCore.Tools.Close.DirtyQuestion");
      IMessage message = new Message(messageText, MessageType.WARNING);
      DialogButtonConfiguration buttonConfiguration = new YesNoDialogButtonConfiguration(resources);
      MessageUserDialogConfiguration configuration = new MessageUserDialogConfiguration(message, buttonConfiguration);
      UserDialog userDialog = new UserDialog(parentComponent, configuration);
      DialogResult result = userDialog.show();
      if (result.isCanceled()) {
        return;
      }
    }
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    management.removeItem(selectedItem);
    management.removeListener(itemActionEnabler);
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }
}
