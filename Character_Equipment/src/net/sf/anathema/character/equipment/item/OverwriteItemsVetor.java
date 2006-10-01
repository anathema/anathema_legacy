package net.sf.anathema.character.equipment.item;

import java.awt.Component;

import net.disy.commons.core.message.IMessage;
import net.disy.commons.core.message.Message;
import net.disy.commons.core.message.MessageType;
import net.disy.commons.swing.dialog.message.MessageUserDialogConfiguration;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.disy.commons.swing.dialog.userdialog.buttons.AbstractDialogButtonConfiguration;

public class OverwriteItemsVetor {

  private final Component parentComponent;

  public OverwriteItemsVetor(Component parentComponent) {
    this.parentComponent = parentComponent;
  }

  public boolean vetos() {
    String messageText = "Really overwrite existing item?";
    IMessage message = new Message(messageText, MessageType.WARNING);
    MessageUserDialogConfiguration configuration = new MessageUserDialogConfiguration(
        message,
        new AbstractDialogButtonConfiguration() {
          @Override
          public String getOkayButtonText() {
            return "Overwrite";
          }
        });
    UserDialog userDialog = new UserDialog(parentComponent, configuration);
    userDialog.show();
    return userDialog.isCanceled();
  }

}
