package net.sf.anathema.initialization.modules;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public class PresentationInitializer extends AbstractModuleInitializer {

  private final IResources resources;
  private final IAnathemaView anathemaView;
  private final IAnathemaModel model;

  public PresentationInitializer(
      IModuleCollection moduleCollection,
      IResources resources,
      IAnathemaModel model,
      IAnathemaView anathemaView) {
    super(moduleCollection);
    this.resources = resources;
    this.model = model;
    this.anathemaView = anathemaView;
  }

  @Override
  protected void initialize(IAnathemaModule module) {
    module.initPresentation(resources, model, anathemaView);
  }
}
