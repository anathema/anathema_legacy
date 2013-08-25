package net.sf.anathema.framework.swing;

import javafx.stage.Window;
import net.sf.anathema.framework.fx.FxDialogExceptionHandler;
import net.sf.anathema.lib.exception.LoggingExceptionHandler;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.NullStringProvider;

public class ExceptionIndicator {

  public static void indicate(Window owner, Message message) {
    Throwable throwable = message.getThrowable();
    new LoggingExceptionHandler().handle(throwable);
    new FxDialogExceptionHandler(new NullStringProvider(), owner).handle(message.getThrowable(), message.getText());
  }
}