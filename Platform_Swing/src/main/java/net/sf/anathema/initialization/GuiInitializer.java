package net.sf.anathema.initialization;

import javafx.stage.Stage;
import net.sf.anathema.ISplashscreen;
import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.framework.module.AnathemaCoreMenu;
import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.resources.LocaleResources;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.framework.view.perspective.PerspectivePaneFactory;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.Resources;

public class GuiInitializer extends Initializer {

  private final Stage stage;

  public GuiInitializer(IInitializationPreferences initializationPreferences, Stage stage) throws InitializationException {
    super(initializationPreferences);
    this.stage = stage;
  }

  @Override
  protected void initPresentation(LocaleResources resources, IApplicationModel model, ApplicationView view) {
    super.initPresentation(resources, model, view);
    new AnathemaCoreMenu().add(resources, model, view.getMenuBar());
  }

  public ApplicationView initialize() throws InitializationException {
    InitializedModelAndView dao = initializeModelViewAndPresentation();
    return dao.view;
  }

  @Override
  protected ApplicationFrameView initView(Resources resources, IApplicationModel anathemaModel, ObjectFactory objectFactory) {
    displayMessage("Building View...");
    AnathemaViewProperties viewProperties = new AnathemaViewProperties(resources);
    PerspectivePaneFactory factory = new PerspectivePaneFactory(anathemaModel, resources, objectFactory);
    return new FxApplicationFrame(stage, viewProperties, factory);
  }

  @Override
  protected void showVersion(Resources resources) {
    Version version = new Version(resources);
    getSplashscreen().displayVersion("v" + version.asString());
    Logger.getLogger(GuiInitializer.class).info("Program version is " + version.asString());
  }

  @Override
  protected void displayMessage(String message) {
    getSplashscreen().displayStatusMessage(message);
  }

  private ISplashscreen getSplashscreen() {
    return ProxySplashscreen.getInstance();
  }
}