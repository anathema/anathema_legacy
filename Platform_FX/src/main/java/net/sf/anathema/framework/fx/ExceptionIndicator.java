package net.sf.anathema.framework.fx;

import javafx.stage.Window;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.environment.exception.LoggingExceptionHandler;
import net.sf.anathema.lib.message.Message;

public class ExceptionIndicator {

  public static void indicate(Resources resources, Window owner, Message message) {
    Throwable throwable = message.getThrowable();
    new LoggingExceptionHandler().handle(throwable);
    new FxDialogExceptionHandler(resources, owner).handle(message.getThrowable(), message.getText());
  }
}