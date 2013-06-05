package net.sf.anathema.lib.gui.dialog.userdialog.buttons;

import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.message.MessageUserDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.message.IMessage;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;

import java.awt.Component;

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
        new DialogButtonConfiguration() {
          @Override
          public IActionConfiguration getOkActionConfiguration() {
            return new ActionConfiguration(okButtonText);
          }
        });
    UserDialog userDialog = new UserDialog(parentComponent, configuration);
    DialogResult result = userDialog.show();
    return result.isCanceled();
  }
}