package net.sf.anathema.character.equipment.item;

import net.disy.commons.swing.dialog.core.IDialogResult;
import net.disy.commons.swing.dialog.message.MessageUserDialogConfiguration;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.disy.commons.swing.dialog.userdialog.buttons.DialogButtonConfiguration;
import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.message.IMessage;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Component;

public class OverwriteItemsVetor {

  private final Component parentComponent;
  private final IResources resources;

  public OverwriteItemsVetor(Component parentComponent, IResources resources) {
    this.parentComponent = parentComponent;
    this.resources = resources;
  }

  public boolean vetos() {
    String messageText = resources.getString("Equipment.Creation.OverwriteMessage.Text"); //$NON-NLS-1$
    IMessage message = new Message(messageText, MessageType.WARNING);
    MessageUserDialogConfiguration configuration = new MessageUserDialogConfiguration(
        message,
        new DialogButtonConfiguration() {
          @Override
          public IActionConfiguration getOkActionConfiguration() {
            return new ActionConfiguration(resources.getString("Equipment.Creation.OverwriteMessage.OKButton")); //$NON-NLS-1$
          }
        });
    UserDialog userDialog = new UserDialog(parentComponent, configuration);
    IDialogResult result = userDialog.show();
    return result.isCanceled();
  }

}
