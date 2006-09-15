package net.sf.anathema.character.equipment.item;

import java.awt.Component;

import net.disy.commons.core.message.IMessage;
import net.disy.commons.core.message.Message;
import net.disy.commons.core.message.MessageType;
import net.disy.commons.swing.dialog.message.MessageUserDialogConfiguration;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.disy.commons.swing.dialog.userdialog.buttons.AbstractDialogButtonConfiguration;

public class DeleteItemsVetor {

  private final Component parentComponent;

  public DeleteItemsVetor(Component parentComponent) {
    this.parentComponent = parentComponent;
  }

  public boolean vetos() {
    String messageText = "Really delete this item?";
    IMessage message = new Message(messageText, MessageType.WARNING);
    MessageUserDialogConfiguration configuration = new MessageUserDialogConfiguration(
        message,
        new AbstractDialogButtonConfiguration() {
          @Override
          public String getOkayButtonText() {
            return "Delete";
          }
        });
    UserDialog userDialog = new UserDialog(parentComponent, configuration);
    userDialog.show();
    return userDialog.isCanceled();
  }

}
