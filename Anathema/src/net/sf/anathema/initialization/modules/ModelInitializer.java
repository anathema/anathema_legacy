package net.sf.anathema.initialization.modules;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.lib.resources.IResources;

public class ModelInitializer extends AbstractModuleInitializer {

  private final IAnathemaModel model;
  private final IResources resources;

  public ModelInitializer(IModuleCollection moduleCollection, IAnathemaModel model, IResources resources) {
    super(moduleCollection);
    this.model = model;
    this.resources = resources;
  }

  @Override
  protected void initialize(IAnathemaModule module) {
    module.initModel(model, resources);
  }
}