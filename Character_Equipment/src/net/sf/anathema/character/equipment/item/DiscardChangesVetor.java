package net.sf.anathema.character.equipment.item;

import java.awt.Component;

import net.disy.commons.core.message.IMessage;
import net.disy.commons.core.message.Message;
import net.disy.commons.core.message.MessageType;
import net.disy.commons.swing.dialog.message.MessageUserDialogConfiguration;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.disy.commons.swing.dialog.userdialog.buttons.AbstractDialogButtonConfiguration;
import net.sf.anathema.lib.gui.list.veto.IVetor;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class DiscardChangesVetor implements IVetor {

  private final ICondition preCondition;
  private final Component parentComponent;

  public DiscardChangesVetor(ICondition preCondition, Component parentComponent) {
    this.preCondition = preCondition;
    this.parentComponent = parentComponent;
  }

  public boolean vetos() {
    if (!preCondition.isFullfilled()) {
      return false;
    }
    String messageText = "You have unsaved changes. Discard them?";
    IMessage message = new Message(messageText, MessageType.WARNING);
    MessageUserDialogConfiguration configuration = new MessageUserDialogConfiguration(
        message,
        new AbstractDialogButtonConfiguration() {
          @Override
          public String getOkayButtonText() {
            return "Discard changes";
          }
        });
    UserDialog userDialog = new UserDialog(parentComponent, configuration);
    userDialog.show();
    return userDialog.isCanceled();
  }
}