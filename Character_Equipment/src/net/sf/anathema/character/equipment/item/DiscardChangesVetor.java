package net.sf.anathema.character.equipment.item;

import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.data.Condition;
import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.message.MessageUserDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.DialogButtonConfiguration;
import net.sf.anathema.lib.gui.list.veto.Vetor;
import net.sf.anathema.lib.message.IMessage;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.resources.Resources;

public class DiscardChangesVetor implements Vetor {

  private final Condition preCondition;
  private final Resources resources;

  public DiscardChangesVetor(Resources resources, Condition preCondition) {
    this.resources = resources;
    this.preCondition = preCondition;
  }

  @Override
  public boolean vetos() {
    if (!preCondition.isFulfilled()) {
      return false;
    }
    String messageText = resources.getString("Equipment.Creation.UnsavedChangesMessage.Text");
    final String okButtonText = resources.getString("Equipment.Creation.UnsavedChangesMessage.OKButton");
    IMessage message = new Message(messageText, MessageType.WARNING);
    MessageUserDialogConfiguration configuration = new MessageUserDialogConfiguration(message,
            new DialogButtonConfiguration() {
              @Override
              public IActionConfiguration getOkActionConfiguration() {
                return new ActionConfiguration(okButtonText);
              }
            });
    UserDialog userDialog = new UserDialog(SwingApplicationFrame.getParentComponent(), configuration);
    DialogResult result = userDialog.show();
    return result.isCanceled();
  }

  @Override
  public void requestPermissionFor(Command command) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}