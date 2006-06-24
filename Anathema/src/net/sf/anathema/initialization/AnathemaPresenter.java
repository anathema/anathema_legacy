package net.sf.anathema.initialization;

import java.util.Collection;

import net.sf.anathema.development.DevelopmentEnvironmentPresenter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
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
  private final Collection<AbstractItemTypeConfiguration> itemTypeConfigurations;

  public AnathemaPresenter(
      IAnathemaModel model,
      IAnathemaView view,
      IAnathemaResources resources,
      Collection<AbstractItemTypeConfiguration> itemTypeConfigurations) {
    this.model = model;
    this.view = view;
    this.resources = resources;
    this.itemTypeConfigurations = itemTypeConfigurations;
  }

  public void initPresentation(IModuleCollection moduleCollection) {
    new PresentationExtensionPointInitializer(moduleCollection, model.getExtensionPointRegistry(), resources).initialize();
    for (AbstractItemTypeConfiguration configuration : itemTypeConfigurations) {
      configuration.fillPresentationExtensionPoints(model.getExtensionPointRegistry(), resources, model, view);
    }
    new PresentationExtensionPointFiller(moduleCollection, model, view, resources).initialize();
    for (AbstractItemTypeConfiguration configuration : itemTypeConfigurations) {
      configuration.registerViewFactory(model, resources);
    }
    new PresentationInitializer(moduleCollection, resources, model, view).initialize();
    if (AnathemaEnvironment.isDevelopment()) {
      new DevelopmentEnvironmentPresenter(model, view, resources).initPresentation();
    }
  }
}