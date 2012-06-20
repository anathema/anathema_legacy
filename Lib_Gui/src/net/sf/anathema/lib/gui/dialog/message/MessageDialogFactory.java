package net.sf.anathema.lib.gui.dialog.message;

import net.sf.anathema.lib.gui.dialog.foldout.FoldOutDialog;
import net.sf.anathema.lib.gui.dialog.foldout.IFoldOutPage;
import net.sf.anathema.lib.gui.dialog.message.internal.MessageDetailsFoldOutPage;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.message.IMessage;

import java.awt.Component;

public class MessageDialogFactory {

  public static UserDialog createMessageDialog(Component parentComponent, IMessage message) {
    UserDialog userDialog;
    if (message.getDetail() == null) {
      userDialog = new UserDialog(parentComponent, new MessageUserDialogConfiguration(message));
      userDialog.getDialog().setResizable(false);
    } else {
      IFoldOutPage foldOutPage = new MessageDetailsFoldOutPage(message.getDetail());
      FoldOutMessageDialogConfiguration dialogConfiguration = new FoldOutMessageDialogConfiguration(message,
              foldOutPage);
      userDialog = new FoldOutDialog(parentComponent, dialogConfiguration);
    }
    return userDialog;
  }

  public static void showMessageDialog(Component parent, IMessage message) {
    createMessageDialog(parent, message).show();
  }
}