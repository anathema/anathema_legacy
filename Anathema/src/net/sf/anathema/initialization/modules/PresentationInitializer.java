package net.sf.anathema.initialization.modules;

import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public class PresentationInitializer extends AbstractModuleInitializer {

  private final IResources resources;
  private final IAnathemaView anathemaView;

  public PresentationInitializer(IModuleCollection moduleCollection, IResources resources, IAnathemaView anathemaView) {
    super(moduleCollection);
    this.resources = resources;
    this.anathemaView = anathemaView;
  }

  @Override
  protected void initialize(IAnathemaModule module) {
    module.initPresentation(resources, anathemaView);
  }
}
