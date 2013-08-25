package net.sf.anathema.framework.view;

import net.sf.anathema.framework.swing.ExceptionIndicator;
import net.sf.anathema.lib.message.Message;

public class ErrorWindow implements ApplicationFrame {

  private Exception cause;

  public ErrorWindow(Exception cause) {
    this.cause = cause;
  }

  @Override
  public void show() {
    Message message = new Message(cause.getMessage(), cause);
    ExceptionIndicator.indicate(null, message);
  }
}