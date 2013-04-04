package net.sf.anathema.character.equipment.item;

import net.sf.anathema.lib.data.ICondition;
import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.gui.dialog.core.IDialogResult;
import net.sf.anathema.lib.gui.dialog.message.MessageUserDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.DialogButtonConfiguration;
import net.sf.anathema.lib.gui.list.veto.IVetor;
import net.sf.anathema.lib.message.IMessage;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.resources.Resources;

import java.awt.Component;

public class DiscardChangesVetor implements IVetor {

  private final ICondition preCondition;
  private final Component parentComponent;
  private final Resources resources;

  public DiscardChangesVetor(Resources resources, ICondition preCondition, Component parentComponent) {
    this.resources = resources;
    this.preCondition = preCondition;
    this.parentComponent = parentComponent;
  }

  @Override
  public boolean vetos() {
    if (!preCondition.isFulfilled()) {
      return false;
    }
    String messageText = resources.getString("Equipment.Creation.UnsavedChangesMessage.Text");
    IMessage message = new Message(messageText, MessageType.WARNING);
    MessageUserDialogConfiguration configuration = new MessageUserDialogConfiguration(
        message,
        new DialogButtonConfiguration() {
          @Override
          public IActionConfiguration getOkActionConfiguration() {
            return new ActionConfiguration(resources.getString("Equipment.Creation.UnsavedChangesMessage.OKButton"));
          }
        });
    UserDialog userDialog = new UserDialog(parentComponent, configuration);
    IDialogResult result = userDialog.show();
    return result.isCanceled();
  }
}