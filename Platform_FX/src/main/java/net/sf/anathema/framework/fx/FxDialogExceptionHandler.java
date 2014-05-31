package net.sf.anathema.framework.fx;

import javafx.stage.Window;
import net.sf.anathema.framework.environment.ExceptionHandler;
import net.sf.anathema.framework.environment.Resources;
import org.controlsfx.dialog.Dialogs;

import static org.controlsfx.dialog.DialogStyle.NATIVE;

public class FxDialogExceptionHandler implements ExceptionHandler {

  private final Resources resources;
  private final Window stage;

  public FxDialogExceptionHandler(Resources resources, Window stage) {
    this.resources = resources;
    this.stage = stage;
  }

  @Override
  public void handle(Throwable throwable) {
    if (throwable instanceof Exception) {
      String message = getString("CentralExceptionHandling.ExceptionOccured.Message");
      indicateException((Exception) throwable, message);
    } else {
      String message = getString("CentralExceptionHandling.ErrorOccured.Message");
      indicateError(throwable, message);
    }
  }

  @Override
  public void handle(Throwable throwable, String message) {
    if (throwable instanceof Exception) {
      indicateException((Exception) throwable, message);
    } else {
      indicateError(throwable, message);
    }
  }

  @SuppressWarnings("UnusedParameters")
  protected void indicateError(final Throwable throwable, final String message) {
    String title = getString("CentralExceptionHandling.ErrorOccured.Title");
    createDialog(title, message).showException(throwable);
    System.exit(0);
  }

  private Dialogs createDialog(String title, String message) {
    return Dialogs.create().owner(stage).style(NATIVE).masthead(message).title(title);
  }

  protected void indicateException(final Exception exception, final String message) {
    String title = getString("CentralExceptionHandling.ExceptionOccured.Title");
    createDialog(title, message).showException(exception);
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }
}