package net.sf.anathema.framework.swing;

import javafx.stage.Window;
import net.sf.anathema.lib.exception.LoggingExceptionHandler;
import net.sf.anathema.lib.gui.dialog.message.MessageDialogFactory;
import net.sf.anathema.lib.message.Message;

public class ExceptionIndicator {

  public static void indicate(Window parentComponent, Message message) {
    Throwable throwable = message.getThrowable();
    new LoggingExceptionHandler().handle(throwable);
    MessageDialogFactory.showMessageDialog(parentComponent, message);
  }
}