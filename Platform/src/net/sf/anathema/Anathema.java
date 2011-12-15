package net.sf.anathema;

import net.sf.anathema.framework.configuration.AnathemaPreferences;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.view.ErrorWindow;
import net.sf.anathema.framework.view.IWindow;
import net.sf.anathema.initialization.AnathemaInitializer;
import net.sf.anathema.initialization.InitializationException;
import org.java.plugin.PluginManager;
import org.java.plugin.boot.Application;

import javax.swing.*;

public class Anathema implements Application {

  private final PluginManager manager;

  public Anathema(PluginManager manager) {
    this.manager = manager;
  }

  public void startApplication() throws Exception {
    IAnathemaPreferences anathemaPreferences = loadPreferences();
    prepareEnvironment(anathemaPreferences);
    showMainFrame(anathemaPreferences);
  }

  private IAnathemaPreferences loadPreferences() {
    String message = "Retrieving Preferences..."; //$NON-NLS-1$
    displayStatus(message);
    return AnathemaPreferences.getDefaultPreferences();
  }

  private void prepareEnvironment(IAnathemaPreferences anathemaPreferences) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
    displayStatus("Preparing Environment..."); //$NON-NLS-1$
    AnathemaEnvironment.initLogging();
    AnathemaEnvironment.initLocale(anathemaPreferences);
    AnathemaEnvironment.initTooltipManager(anathemaPreferences);
  }

  private void showMainFrame(IAnathemaPreferences anathemaPreferences) {
    IWindow anathemaView = createView(anathemaPreferences);
    displayStatus("Done.");
    anathemaView.show();
  }

  private IWindow createView(IAnathemaPreferences anathemaPreferences) {
    try {
      displayStatus("Starting Platform..."); //$NON-NLS-1$
      return new AnathemaInitializer(manager, anathemaPreferences).initialize();
    } catch (InitializationException e) {
      e.printStackTrace();
      return new ErrorWindow(e);
    }
  }

  private void displayStatus(String message) {
    ProxySplashscreen.getInstance().displayStatusMessage(message);
  }
}