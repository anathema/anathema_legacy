package net.sf.anathema;

import javafx.application.Application;
import javafx.stage.Stage;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.exception.ExceptionHandling;
import net.sf.anathema.framework.environment.exception.ExtensibleExceptionHandler;
import net.sf.anathema.framework.repository.tree.FxFileChooser;
import net.sf.anathema.framework.view.ApplicationFrame;
import net.sf.anathema.initialization.EnvironmentFactory;
import net.sf.anathema.initialization.GuiInitializer;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.logging.Logger;

public class Anathema extends Application {

  private Environment environment;
  private ExtensibleExceptionHandler exceptionHandler;
  private ProxyFileChooser fileChooser;

  /*Called by the boot loader using reflection.*/
  @SuppressWarnings("UnusedDeclaration")
  public void startApplication() throws Exception {
    launch();
  }

  @Override
  public void init() throws Exception {
    Logger.getLogger(Anathema.class).info("Launching Anathema");
    this.exceptionHandler = new ExceptionHandling().create();
    this.fileChooser = new ProxyFileChooser();
    this.environment = new EnvironmentFactory(exceptionHandler, fileChooser).create();
  }

  @Override
  public void start(Stage stage) throws Exception {
    try {
      displayStatus("Initializing Environment...");
      fileChooser.setDelegate(new FxFileChooser(stage));
      displayStatus("Starting Platform...");
      ApplicationFrame applicationFrame = new GuiInitializer(stage, environment, exceptionHandler).initialize().getWindow();
      displayStatus("Done.");
      applicationFrame.show();
    } catch (InitializationException e) {
      environment.handle(e);
    }
  }

  private void displayStatus(String message) {
    ProxySplashscreen.getInstance().displayStatusMessage(message);
  }
}