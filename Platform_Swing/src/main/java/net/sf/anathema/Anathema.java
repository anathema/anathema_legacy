package net.sf.anathema;

import com.itextpdf.text.log.LoggerFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.framework.configuration.InitializationPreferences;
import net.sf.anathema.framework.environment.LocaleEnvironment;
import net.sf.anathema.framework.view.ApplicationFrame;
import net.sf.anathema.framework.view.ErrorWindow;
import net.sf.anathema.initialization.GuiInitializer;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.logging.Logger;

public class Anathema extends Application {

  private IInitializationPreferences initializationPreferences;

  /*Called by the boot loader using reflection.*/
  @SuppressWarnings("UnusedDeclaration")
  public void startApplication() throws Exception {
    launch();
  }

  @Override
  public void init() throws Exception {
    Logger.getLogger(Anathema.class).info("Launching Anathema");
    this.initializationPreferences = loadPreferences();
    displayStatus("Preparing Environment...");
    LocaleEnvironment.initLocale(initializationPreferences);
  }

  private IInitializationPreferences loadPreferences() {
    displayStatus("Retrieving Preferences...");
    return InitializationPreferences.getDefaultPreferences();
  }

  @Override
  public void start(Stage stage) throws Exception {
    ApplicationFrame anathemaView = createView(stage);
    displayStatus("Done.");
    anathemaView.show();
  }

  private ApplicationFrame createView(Stage stage) {
    try {
      displayStatus("Starting Platform...");
      return new GuiInitializer(initializationPreferences, stage).initialize().getWindow();
    } catch (InitializationException e) {
      LoggerFactory.getLogger(Anathema.class).error("Could not start platform.", e);
      return new ErrorWindow(e);
    }
  }

  private void displayStatus(String message) {
    ProxySplashscreen.getInstance().displayStatusMessage(message);
  }
}