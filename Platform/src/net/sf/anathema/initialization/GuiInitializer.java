package net.sf.anathema.initialization;

import net.sf.anathema.ISplashscreen;
import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.view.AnathemaMainView;
import net.sf.anathema.framework.view.MainView;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

public class GuiInitializer extends Initializer {

  public GuiInitializer(IInitializationPreferences initializationPreferences) throws InitializationException {
    super(initializationPreferences);
  }

  public MainView initialize() throws InitializationException {
    InitializedModelAndView dao = initializeModelViewAndPresentation();
    return dao.view;
  }

  @Override
  protected MainView initView(IResources resources) {
    displayMessage("Building View...");
    AnathemaViewProperties viewProperties = new AnathemaViewProperties(resources, getPreferences().initMaximized());
    return new AnathemaMainView(viewProperties);
  }

  @Override
  protected void showVersion(IResources resources) {
    Version version = new Version(resources);
    getSplashscreen().displayVersion("v" + version.asString());
    Logger.getLogger(GuiInitializer.class).info("Program version is " + version.asString());
  }

  @Override
  protected void displayMessage(String message) {
    getSplashscreen().displayStatusMessage(message); //$NON-NLS-1$
  }

  private ISplashscreen getSplashscreen() {
    return ProxySplashscreen.getInstance();
  }
}