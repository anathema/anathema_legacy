package net.sf.anathema.initialization;

import javafx.stage.Stage;
import net.sf.anathema.ISplashscreen;
import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.framework.environment.exception.ExtensibleExceptionHandler;
import net.sf.anathema.framework.fx.FxDialogExceptionHandler;
import net.sf.anathema.framework.module.AnathemaCoreMenu;
import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.framework.view.perspective.PerspectivePaneFactory;
import net.sf.anathema.lib.logging.Logger;

public class GuiInitializer extends Initializer {

  private final Stage stage;
  private final UiEnvironment uiEnvironment;
  private final ExtensibleExceptionHandler exceptionHandler;

  public GuiInitializer(Stage stage, Environment environment, UiEnvironment uiEnvironment, ExtensibleExceptionHandler exceptionHandler) throws InitializationException {
    super(environment);
    this.stage = stage;
    this.uiEnvironment = uiEnvironment;
    this.exceptionHandler = exceptionHandler;
  }

  @Override
  protected void initPresentation(Environment environment, IApplicationModel model, ApplicationView view) {
    super.initPresentation(environment, model, view);
    new AnathemaCoreMenu().add(environment, uiEnvironment, model, view.getMenuBar());
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
    PerspectivePaneFactory factory = new PerspectivePaneFactory(anathemaModel, environment, objectFactory, uiEnvironment);
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