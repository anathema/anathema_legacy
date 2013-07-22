package net.sf.anathema;

import com.itextpdf.text.log.LoggerFactory;
import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.framework.configuration.InitializationPreferences;
import net.sf.anathema.framework.environment.LocaleEnvironment;
import net.sf.anathema.framework.environment.SwingEnvironment;
import net.sf.anathema.framework.view.ApplicationFrame;
import net.sf.anathema.framework.view.ErrorWindow;
import net.sf.anathema.initialization.GuiInitializer;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.logging.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.net.URLClassLoader;
import java.util.Properties;
import java.lang.reflect.Method;

import javax.swing.SwingUtilities;

public class Anathema {

  public static void main( String [] args ) {
    Anathema app = new Anathema();
  }
  
  public Anathema() {
    Logger.getLogger(Anathema.class).info("Launching Anathema");
    IInitializationPreferences initializationPreferences = loadPreferences();
    prepareEnvironment(initializationPreferences);
    showMainFrame(initializationPreferences);
  }

  private IInitializationPreferences loadPreferences() {
    displayStatus("Retrieving Preferences...");
    return InitializationPreferences.getDefaultPreferences();
  }

  private void prepareEnvironment(IInitializationPreferences initializationPreferences) {
    displayStatus("Preparing Environment...");
    try {
      LocaleEnvironment.initLocale(initializationPreferences);
      SwingEnvironment.initLookAndFeel(initializationPreferences);
      SwingEnvironment.initTooltipManager(initializationPreferences);
    } catch( ClassNotFoundException | InstantiationException | IllegalAccessException e ) {
      Logger.getLogger(Anathema.class).error( "Could not prepare environment. ", e);
    }
  }

  private void showMainFrame(final IInitializationPreferences initializationPreferences) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        ApplicationFrame anathemaView = createView(initializationPreferences);
        displayStatus("Done.");
        anathemaView.show();
      }
    });
  }

  private ApplicationFrame createView(IInitializationPreferences initializationPreferences) {
    try {
      displayStatus("Starting Platform...");
      return new GuiInitializer(initializationPreferences).initialize().getWindow();
    } catch (InitializationException e) {
      LoggerFactory.getLogger(Anathema.class).error("Could not start platform.", e);
      return new ErrorWindow(e);
    }
  }

  private void displayStatus(String message) {
    ProxySplashscreen.getInstance().displayStatusMessage(message);
  }

}