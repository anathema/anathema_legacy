package net.sf.anathema.lib.gui.dialog;

import java.awt.Component;

import net.disy.commons.core.message.IMessage;
import net.disy.commons.core.message.Message;
import net.disy.commons.core.message.MessageType;
import net.disy.commons.swing.dialog.message.MessageUserDialogConfiguration;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.disy.commons.swing.dialog.userdialog.buttons.AbstractDialogButtonConfiguration;

public class ConfigurableVetor {

  private final Component parentComponent;
  private final String messageText;
  private final String okButtonText;

  public ConfigurableVetor(Component parentComponent, String messageText, String okButtonText) {
    this.parentComponent = parentComponent;
    this.messageText = messageText;
    this.okButtonText = okButtonText;
  }

  public boolean vetos() {
    IMessage message = new Message(messageText, MessageType.WARNING);
    MessageUserDialogConfiguration configuration = new MessageUserDialogConfiguration(
        message,
        new AbstractDialogButtonConfiguration() {
          @Override
          public String getOkayButtonText() {
            return okButtonText;
          }
        });
    UserDialog userDialog = new UserDialog(parentComponent, configuration);
    userDialog.show();
    return userDialog.isCanceled();
  }
}