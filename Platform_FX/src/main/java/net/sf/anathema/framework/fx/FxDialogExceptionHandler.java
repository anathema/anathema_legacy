package net.sf.anathema.framework.fx;

import fr.xmichel.javafx.dialog.Dialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import net.sf.anathema.lib.exception.ExceptionHandler;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.fx.FxThreading;

public class FxDialogExceptionHandler implements ExceptionHandler {

  private final Resources resources;
  private Stage stage;

  public FxDialogExceptionHandler(Resources resources, Stage stage) {
    this.resources = resources;
    this.stage = stage;
  }

  @Override
  public void handle(Throwable exception) {
    if (exception instanceof Exception) {
      indicateException((Exception) exception);
    } else {
      indicateError(exception);
    }
  }

  @SuppressWarnings("UnusedParameters")
  protected void indicateError(final Throwable throwable) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        String message = getString("CentralExceptionHandling.ErrorOccured.Message");
        String title = getString("CentralExceptionHandling.ErrorOccured.Title");
        Dialog.buildConfirmation(title, message).addYesButton(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            System.exit(0);
          }
        });
      }
    });
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }

  protected void indicateException(final Exception exception) {
    FxThreading.runInFxAsSoonAsPossible(new Runnable() {
      @Override
      public void run() {
        String title = getString("CentralExceptionHandling.ExceptionOccured.Title");
        String message = getString("CentralExceptionHandling.ExceptionOccured.Message");
        Dialog.showThrowable(title, message, exception, stage);
      }
    });
  }
}