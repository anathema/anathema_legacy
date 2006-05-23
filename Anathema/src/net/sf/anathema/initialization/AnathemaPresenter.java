package net.sf.anathema.initialization;

import net.sf.anathema.development.DevelopmentEnvironmentPresenter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.modules.IModuleCollection;
import net.sf.anathema.initialization.modules.PresentationExtensionPointFiller;
import net.sf.anathema.initialization.modules.PresentationExtensionPointInitializer;
import net.sf.anathema.initialization.modules.PresentationInitializer;

public class AnathemaPresenter {

  private final IAnathemaModel model;
  private final IAnathemaView view;
  private final IAnathemaResources resources;

  public AnathemaPresenter(IAnathemaModel model, IAnathemaView view, IAnathemaResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation(IModuleCollection moduleCollection) {
    new PresentationExtensionPointInitializer(moduleCollection, model.getExtensionPointRegistry(), resources).initialize();
    new PresentationExtensionPointFiller(moduleCollection, model, view, resources).initialize();
    new PresentationInitializer(moduleCollection, resources, model, view).initialize();
    if (AnathemaEnvironment.isDevelopment()) {
      new DevelopmentEnvironmentPresenter(model, view, resources).initPresentation();
    }
  }
}