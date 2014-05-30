package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.view.ApplicationView;

public abstract class Initializer {

  private final Environment environment;

  public Initializer(Environment environment) throws InitializationException {
    this.environment = environment;
  }

  protected InitializedModelAndView initializeModelViewAndPresentation() throws InitializationException {
    configureExceptionHandling(environment);
    showVersion(environment);
    IApplicationModel anathemaModel = initModel(environment);
    ApplicationFrameView view = initView(environment, anathemaModel, environment);
    initPresentation(environment, anathemaModel, view);
    return new InitializedModelAndView(view, anathemaModel);
  }

  protected void configureExceptionHandling(Resources resources) {
    //nothing to do
  }

  protected void initPresentation(Environment environment, IApplicationModel anathemaModel, ApplicationView view) {
    AnathemaPresenter presenter = new AnathemaPresenter(anathemaModel, view, environment);
    presenter.initPresentation();
  }

  private IApplicationModel initModel(Environment environment) throws InitializationException {
    displayMessage("Creating Model...");
    AnathemaModelInitializer modelInitializer = new AnathemaModelInitializer();
    return modelInitializer.initializeModel(environment);
  }

  protected abstract void showVersion(Resources resources);

  protected abstract void displayMessage(String message);

  protected abstract ApplicationFrameView initView(Environment environment, IApplicationModel anathemaModel, ObjectFactory objectFactory);
}