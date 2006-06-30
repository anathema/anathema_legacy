package net.sf.anathema.initialization.modules;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.IAnathemaModule;

public class ModelInitializer extends AbstractModuleInitializer {

  private final IAnathemaModel model;
  public ModelInitializer(IModuleCollection moduleCollection, IAnathemaModel model) {
    super(moduleCollection);
    this.model = model;
  }

  @Override
  protected void initialize(IAnathemaModule module) {
    module.initModel(model);
  }
}