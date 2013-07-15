package net.sf.anathema.character.equipment.item;

import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.message.MessageUserDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.DialogButtonConfiguration;
import net.sf.anathema.lib.message.IMessage;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.resources.Resources;

import java.awt.Component;

public class OverwriteItemsVetor {

  private final Component parentComponent;
  private final Resources resources;

  public OverwriteItemsVetor(Component parentComponent, Resources resources) {
    this.parentComponent = parentComponent;
    this.resources = resources;
  }

  public boolean vetos() {
    String messageText = resources.getString("Equipment.Creation.OverwriteMessage.Text");
    IMessage message = new Message(messageText, MessageType.WARNING);
    MessageUserDialogConfiguration configuration = new MessageUserDialogConfiguration(
        message,
        new DialogButtonConfiguration() {
          @Override
          public IActionConfiguration getOkActionConfiguration() {
            return new ActionConfiguration(resources.getString("Equipment.Creation.OverwriteMessage.OKButton"));
          }
        });
    UserDialog userDialog = new UserDialog(parentComponent, configuration);
    DialogResult result = userDialog.show();
    return result.isCanceled();
  }

}
