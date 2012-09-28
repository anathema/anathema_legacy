package net.sf.anathema;

import com.itextpdf.text.log.LoggerFactory;
import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.framework.configuration.InitializationPreferences;
import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.view.ErrorWindow;
import net.sf.anathema.framework.view.IWindow;
import net.sf.anathema.initialization.AnathemaInitializer;
import net.sf.anathema.initialization.InitializationException;

import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

public class Anathema {

  /*Called by the boot loader using reflection.*/
  @SuppressWarnings("UnusedDeclaration")
  public void startApplication() throws Exception {
    IInitializationPreferences initializationPreferences = loadPreferences();
    prepareEnvironment(initializationPreferences);
    showMainFrame(initializationPreferences);
  }

  private IInitializationPreferences loadPreferences() {
    displayStatus("Retrieving Preferences...");
    return InitializationPreferences.getDefaultPreferences();
  }

  private void prepareEnvironment(IInitializationPreferences initializationPreferences) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
    displayStatus("Preparing Environment..."); //$NON-NLS-1$
    AnathemaEnvironment.initLocale(initializationPreferences);
    AnathemaEnvironment.initLookAndFeel(initializationPreferences);
    AnathemaEnvironment.initTooltipManager(initializationPreferences);
  }

  private void showMainFrame(final IInitializationPreferences initializationPreferences) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        IWindow anathemaView = createView(initializationPreferences);
        displayStatus("Done.");
        anathemaView.show();
      }
    });
  }

  private IWindow createView(IInitializationPreferences initializationPreferences) {
    try {
      displayStatus("Starting Platform..."); //$NON-NLS-1$
      return new AnathemaInitializer(initializationPreferences).initialize();
    } catch (InitializationException e) {
      LoggerFactory.getLogger(Anathema.class).error("Could not start platform.", e);
      return new ErrorWindow(e);
    }
  }

  private void displayStatus(String message) {
    ProxySplashscreen.getInstance().displayStatusMessage(message);
  }
}