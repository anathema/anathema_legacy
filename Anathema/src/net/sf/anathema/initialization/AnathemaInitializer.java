package net.sf.anathema.initialization;

import java.awt.AWTException;

import net.disy.commons.core.exception.CentralExceptionHandling;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.InitializationException;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.exception.CentralExceptionHandler;
import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.framework.view.AnathemaView;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.modules.IModuleCollection;
import net.sf.anathema.initialization.modules.ResourcesInitializer;

public class AnathemaInitializer {

  private final IModuleCollection moduleCollection = new ModuleCollection();
  private final IAnathemaPreferences anathemaPreferences;

  public AnathemaInitializer(IAnathemaPreferences anathemaPreferences) throws InitializationException {
    this.anathemaPreferences = anathemaPreferences;
  }

  public IAnathemaView initialize() throws Exception {
    IAnathemaResources resources = initResources();
    CentralExceptionHandling.setHandler(new CentralExceptionHandler(resources));
    IAnathemaModel anathemaModel = initModel();
    IAnathemaView view = initView(resources);
    new AnathemaPresenter(anathemaModel, view, resources).initPresentation(moduleCollection);
    return view;
  }

  private IAnathemaModel initModel() throws RepositoryException {
    return new AnathemaModelInitializer(anathemaPreferences).initializeModel(moduleCollection);
  }

  private IAnathemaView initView(IAnathemaResources resources) throws AWTException {
    AnathemaViewProperties viewProperties = new AnathemaViewProperties(resources, anathemaPreferences.initMaximized());
    return new AnathemaView(viewProperties);
  }

  private IAnathemaResources initResources() {
    IAnathemaResources resources = new AnathemaResources();
    new ResourcesInitializer(moduleCollection, resources).initialize();
    return resources;
  }
}