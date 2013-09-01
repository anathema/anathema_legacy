package net.sf.anathema.initialization;

import javafx.stage.Stage;
import net.sf.anathema.ISplashscreen;
import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.configuration.RepositoryPreference;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.fx.FxDialogExceptionHandler;
import net.sf.anathema.framework.module.AnathemaCoreMenu;
import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.environment.resources.LocaleResources;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.framework.view.perspective.PerspectivePaneFactory;
import net.sf.anathema.framework.environment.exception.ExtensibleExceptionHandler;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.framework.environment.Resources;

public class GuiInitializer extends Initializer {

  private final Stage stage;
  private final ExtensibleExceptionHandler exceptionHandler;

  public GuiInitializer(RepositoryPreference initializationPreferences, Stage stage, ExtensibleExceptionHandler exceptionHandler) throws InitializationException {
    super(initializationPreferences, exceptionHandler);
    this.stage = stage;
    this.exceptionHandler = exceptionHandler;
  }

  @Override
  protected void initPresentation(LocaleResources resources, IApplicationModel model, ApplicationView view) {
    super.initPresentation(resources, model, view);
    new AnathemaCoreMenu(stage).add(resources, model, view.getMenuBar());
  }

  public ApplicationView initialize() throws InitializationException {
    InitializedModelAndView dao = initializeModelViewAndPresentation();
    return dao.view;
  }

  protected void configureExceptionHandling(Resources resources) {
    exceptionHandler.addHandler(new FxDialogExceptionHandler(resources, stage));
  }

  @Override
  protected ApplicationFrameView initView(Environment environment, IApplicationModel anathemaModel, ObjectFactory objectFactory) {
    displayMessage("Building View...");
    AnathemaViewProperties viewProperties = new AnathemaViewProperties(environment);
    PerspectivePaneFactory factory = new PerspectivePaneFactory(anathemaModel, environment, objectFactory);
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