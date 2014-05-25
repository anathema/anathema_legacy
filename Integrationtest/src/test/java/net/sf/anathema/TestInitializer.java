package net.sf.anathema;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.initialization.ApplicationFrameView;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.InitializedModelAndView;
import net.sf.anathema.initialization.Initializer;
import net.sf.anathema.view.NullMainView;


public class TestInitializer extends Initializer {

  public static TestInitializer Create() throws InitializationException {
    return new TestInitializer(new DummyEnvironment());
  }

  private Environment environment;

  private TestInitializer(Environment environment) throws InitializationException {
    super(environment);
    this.environment = environment;
  }

  public IApplicationModel initialize() {
    InitializedModelAndView dao = initializeModelViewAndPresentation();
    return dao.model;
  }

  @Override
  protected void showVersion(Resources resources) {
    //nothing to do
  }

  @Override
  protected void displayMessage(String message) {
    //nothing to do
  }

  @Override
  protected ApplicationFrameView initView(Environment environment, IApplicationModel anathemaModel,
                                          ObjectFactory objectFactory) {
    return new NullMainView();
  }

  public Environment getEnvironment() {
    return environment;
  }
}