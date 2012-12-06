package net.sf.anathema;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.MainView;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.InitializedModelAndView;
import net.sf.anathema.initialization.Initializer;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.view.NullMainView;

import static net.sf.anathema.framework.configuration.InitializationPreferences.getDefaultPreferences;

public class TestInitializer extends Initializer {
  public TestInitializer() throws InitializationException {
    super(getDefaultPreferences());
  }

  public IAnathemaModel initialize() {
    InitializedModelAndView dao = initializeModelViewAndPresentation();
    return dao.model;
  }

  @Override
  protected void showVersion(IResources resources) {
    //nothing to do
  }

  @Override
  protected void displayMessage(String message) {
    //nothing to do
  }

  @Override
  protected MainView initView(IResources resources) {
    return new NullMainView();
  }
}