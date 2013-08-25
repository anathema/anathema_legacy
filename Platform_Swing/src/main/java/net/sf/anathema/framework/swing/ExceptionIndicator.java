package net.sf.anathema.framework.swing;

import javafx.stage.Window;
import net.sf.anathema.framework.fx.FxDialogExceptionHandler;
import net.sf.anathema.lib.exception.LoggingExceptionHandler;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.NullStringProvider;
import net.sf.anathema.lib.resources.Resources;

public class ExceptionIndicator {

  public static void indicate(Window owner, Message message) {
    NullStringProvider resources = new NullStringProvider();
    indicate(resources, owner, message);
  }

  public static void indicate(Resources resources, Window owner, Message message) {
    Throwable throwable = message.getThrowable();
    new LoggingExceptionHandler().handle(throwable);
    new FxDialogExceptionHandler(resources, owner).handle(message.getThrowable(), message.getText());
  }
}