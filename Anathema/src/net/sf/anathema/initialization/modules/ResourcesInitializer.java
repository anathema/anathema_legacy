package net.sf.anathema.initialization.modules;

import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.framework.resources.IAnathemaResources;

public class ResourcesInitializer extends AbstractModuleInitializer {

  private final IAnathemaResources resources;

  public ResourcesInitializer(IModuleCollection moduleCollection, IAnathemaResources resources) {
    super(moduleCollection);
    this.resources = resources;
  }

  @Override
  protected void initialize(IAnathemaModule module) {
    module.initAnathemaResources(resources);
  }
}