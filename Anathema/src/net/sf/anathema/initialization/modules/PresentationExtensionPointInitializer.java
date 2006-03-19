package net.sf.anathema.initialization.modules;

import net.sf.anathema.framework.extension.IExtensionPoint;
import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class PresentationExtensionPointInitializer extends AbstractModuleInitializer {

  private final IRegistry<String, IExtensionPoint> extensionPointRegistry;
  private final IResources resources;

  public PresentationExtensionPointInitializer(
      IModuleCollection moduleCollection,
      IRegistry<String, IExtensionPoint> extensionPointRegistry,
      IResources resources) {
    super(moduleCollection);
    this.extensionPointRegistry = extensionPointRegistry;
    this.resources = resources;
  }

  @Override
  protected void initialize(IAnathemaModule module) {
    module.initPresentationExtensionPoints(extensionPointRegistry, resources);
  }
}