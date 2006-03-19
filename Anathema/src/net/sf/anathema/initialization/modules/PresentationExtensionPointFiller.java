package net.sf.anathema.initialization.modules;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public class PresentationExtensionPointFiller extends AbstractModuleInitializer {

  private final IResources resources;
  private final IAnathemaView view;
  private final IAnathemaModel model;

  public PresentationExtensionPointFiller(
      IModuleCollection moduleCollection,
      IAnathemaModel model,
      IAnathemaView view,
      IResources resources) {
    super(moduleCollection);
    this.view = view;
    this.model = model;
    this.resources = resources;
  }

  @Override
  protected void initialize(IAnathemaModule module) {
    module.fillPresentationExtensionPoints(model.getExtensionPointRegistry(), model, resources, view);
  }
}