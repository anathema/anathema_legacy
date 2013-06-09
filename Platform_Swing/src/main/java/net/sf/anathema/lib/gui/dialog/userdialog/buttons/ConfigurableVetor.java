package net.sf.anathema.lib.gui.dialog.userdialog.buttons;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.NullCommand;
import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.message.MessageUserDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.DialogCloseHandler;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.message.IMessage;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;

import javax.swing.SwingUtilities;
import java.awt.Component;

public class ConfigurableVetor {

  private final Component parentComponent;
  private final String messageText;
  private final String okButtonText;
  private Command command = new NullCommand();

  public ConfigurableVetor(Component parentComponent, String messageText, String okButtonText) {
    this.parentComponent = parentComponent;
    this.messageText = messageText;
    this.okButtonText = okButtonText;
  }

  public void whenPermissionIsGiven(Command command) {
    this.command = command;
  }

  public void requestPermission() {
    IMessage message = new Message(messageText, MessageType.WARNING);
    final MessageUserDialogConfiguration configuration = new MessageUserDialogConfiguration(message,
            new DialogButtonConfiguration() {
              @Override
              public IActionConfiguration getOkActionConfiguration() {
                return new ActionConfiguration(okButtonText);
              }
            });
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        showDialog(configuration);
      }
    });

  }

  private void showDialog(MessageUserDialogConfiguration configuration) {
    UserDialog userDialog = new UserDialog(parentComponent, configuration);
    userDialog.show(new DialogCloseHandler() {
      @Override
      public void handleDialogClose(DialogResult result) {
        boolean permissionGranted = !result.isCanceled();
        if (permissionGranted) {
          command.execute();
        }
      }
    });
  }
}