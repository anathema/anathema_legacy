package net.sf.anathema.initialization.modules;

import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.module.IAnathemaModule;

public class ItemTypeInitializer extends AbstractModuleInitializer {

  private final IItemTypeRegistry itemTypeRegistry;

  public ItemTypeInitializer(IModuleCollection moduleCollection, IItemTypeRegistry itemTypeRegistry) {
    super(moduleCollection);
    this.itemTypeRegistry = itemTypeRegistry;
  }

  @Override
  protected void initialize(IAnathemaModule module) {
    module.initItemTypes(itemTypeRegistry);
  }
}