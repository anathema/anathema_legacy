package net.sf.anathema.initialization.modules;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IExtensionPoint;
import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class ModelExtensionPointInitializer extends AbstractModuleInitializer {

  private final IRegistry<String, IExtensionPoint> extensionPointRegistry;
  private final IAnathemaModel model;
  private final IResources resources;

  public ModelExtensionPointInitializer(
      IModuleCollection moduleCollection,
      IRegistry<String, IExtensionPoint> extensionPointRegistry,
      IAnathemaModel model,
      IResources resources) {
    super(moduleCollection);
    this.extensionPointRegistry = extensionPointRegistry;
    this.model = model;
    this.resources = resources;
  }

  @Override
  protected void initialize(IAnathemaModule module) {
    module.initModelExtensionPoints(extensionPointRegistry, model, resources);
  }
}