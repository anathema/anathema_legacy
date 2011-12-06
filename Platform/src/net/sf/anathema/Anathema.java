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
    ProxySplashscreen.getInstance().displayStatusMessage("Retrieving Preferences..."); //$NON-NLS-1$
    return AnathemaPreferences.getDefaultPreferences();
  }

  private void prepareEnvironment(IAnathemaPreferences anathemaPreferences) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
    ProxySplashscreen.getInstance().displayStatusMessage("Preparing Environment..."); //$NON-NLS-1$
    AnathemaEnvironment.initLogging();
    AnathemaEnvironment.initLocale(anathemaPreferences);
    AnathemaEnvironment.initLookAndFeel(anathemaPreferences);
    AnathemaEnvironment.initTooltipManager(anathemaPreferences);
  }

  private void showMainFrame(IAnathemaPreferences anathemaPreferences) {
    IWindow anathemaView = createView(anathemaPreferences);
    ProxySplashscreen.getInstance().displayStatusMessage("Done."); //$NON-NLS-1$
    anathemaView.showFrame();
  }

  private IWindow createView(IAnathemaPreferences anathemaPreferences) {
    try {
      ProxySplashscreen.getInstance().displayStatusMessage("Starting Platform..."); //$NON-NLS-1$
      return new AnathemaInitializer(manager, anathemaPreferences).initialize();
    } catch (InitializationException e) {
      e.printStackTrace();
      return new ErrorWindow(e);
    }
  }
}