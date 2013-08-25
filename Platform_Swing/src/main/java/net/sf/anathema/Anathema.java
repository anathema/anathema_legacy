package net.sf.anathema;

import javafx.application.Application;
import javafx.stage.Stage;
import net.sf.anathema.framework.configuration.RepositoryPreference;
import net.sf.anathema.framework.configuration.InitializationPreferences;
import net.sf.anathema.framework.view.ApplicationFrame;
import net.sf.anathema.initialization.GuiInitializer;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.exception.ExceptionHandling;
import net.sf.anathema.lib.exception.ExtensibleExceptionHandler;
import net.sf.anathema.lib.logging.Logger;

public class Anathema extends Application {

  private RepositoryPreference initializationPreferences;
  private ExtensibleExceptionHandler exceptionHandler;

  /*Called by the boot loader using reflection.*/
  @SuppressWarnings("UnusedDeclaration")
  public void startApplication() throws Exception {
    launch();
  }

  @Override
  public void init() throws Exception {
    Logger.getLogger(Anathema.class).info("Launching Anathema");
    this.exceptionHandler = new ExceptionHandling().create();
    displayStatus("Retrieving Preferences...");
    this.initializationPreferences = new InitializationPreferences();
  }

  @Override
  public void start(Stage stage) throws Exception {
    try {
      displayStatus("Starting Platform...");
      ApplicationFrame applicationFrame = new GuiInitializer(initializationPreferences, stage, exceptionHandler).initialize().getWindow();
      displayStatus("Done.");
      applicationFrame.show();
    } catch (InitializationException e) {
      exceptionHandler.handle(e);
    }
  }

  private void displayStatus(String message) {
    ProxySplashscreen.getInstance().displayStatusMessage(message);
  }
}