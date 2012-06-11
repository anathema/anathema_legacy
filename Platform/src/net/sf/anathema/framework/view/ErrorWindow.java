package net.sf.anathema.framework.view;

import net.sf.anathema.lib.gui.dialog.message.MessageDialogFactory;
import net.sf.anathema.lib.message.Message;

public class ErrorWindow implements IWindow {

  private Exception cause;

  public ErrorWindow(Exception cause) {
    this.cause = cause;
  }

  @Override
  public void show() {
    MessageDialogFactory.showMessageDialog(null, new Message(cause.getMessage(), cause));
  }
}