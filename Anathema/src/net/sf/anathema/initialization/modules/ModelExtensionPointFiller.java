package net.sf.anathema.initialization.modules;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.lib.registry.IRegistry;

public class ModelExtensionPointFiller extends AbstractModuleInitializer {

  private final IRegistry<String, IAnathemaExtension> extensionPointRegistry;
  private final IAnathemaModel model;

  public ModelExtensionPointFiller(
      IModuleCollection moduleCollection,
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IAnathemaModel model) {
    super(moduleCollection);
    this.extensionPointRegistry = extensionPointRegistry;
    this.model = model;
  }

  @Override
  protected void initialize(IAnathemaModule module) {
    module.fillModelExtensionPoints(extensionPointRegistry, model);
  }
}