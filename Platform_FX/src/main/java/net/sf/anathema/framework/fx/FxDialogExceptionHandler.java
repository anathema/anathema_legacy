package net.sf.anathema.framework.fx;

//import fr.xmichel.javafx.dialog.Dialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Window;
import net.sf.anathema.framework.environment.ExceptionHandler;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.platform.fx.FxThreading;

public class FxDialogExceptionHandler implements ExceptionHandler {

  private final Resources resources;
  private Window stage;

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
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        String title = getString("CentralExceptionHandling.ErrorOccured.Title");
        /*Dialog.buildConfirmation(title, message).addYesButton(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            System.exit(0);
          }
        });*/
      }
    });
  }

  protected void indicateException(final Exception exception, final String message) {
    FxThreading.runInFxAsSoonAsPossible(new Runnable() {
      @Override
      public void run() {
        String title = getString("CentralExceptionHandling.ExceptionOccured.Title");
        //Dialog.showThrowable(title, message, exception, stage);
      }
    });
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }
}