package net.sf.anathema;

import com.itextpdf.text.log.LoggerFactory;
import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.framework.configuration.InitializationPreferences;
import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.view.ApplicationFrame;
import net.sf.anathema.framework.view.ErrorWindow;
import net.sf.anathema.initialization.GuiInitializer;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.logging.Logger;

import javax.swing.SwingUtilities;

public class Anathema {

  /*Called by the boot loader using reflection.*/
  @SuppressWarnings("UnusedDeclaration")
  public void startApplication() throws Exception {
    Logger.getLogger(Anathema.class).info("Launching Anathema");
    IInitializationPreferences initializationPreferences = loadPreferences();
    prepareEnvironment(initializationPreferences);
    showMainFrame(initializationPreferences);
  }

  private IInitializationPreferences loadPreferences() {
    displayStatus("Retrieving Preferences...");
    return InitializationPreferences.getDefaultPreferences();
  }

  private void prepareEnvironment(IInitializationPreferences initializationPreferences) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    displayStatus("Preparing Environment...");
    AnathemaEnvironment.initLocale(initializationPreferences);
    AnathemaEnvironment.initLookAndFeel(initializationPreferences);
    AnathemaEnvironment.initTooltipManager(initializationPreferences);
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