package net.sf.anathema.cascades.module;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.initialization.ItemTypeConfiguration;

@ItemTypeConfiguration
public class CharmCascadeItemTypeConfiguration implements net.sf.anathema.framework.module.ItemTypeConfiguration {

  public static final String CHARM_CASCADES_ITEM_TYPE_ID = "CharmCascades";
  private IItemType type;

  public CharmCascadeItemTypeConfiguration() {
    this.type = new ItemType(CHARM_CASCADES_ITEM_TYPE_ID, null);
  }

  @Override
  public IItemType getItemType() {
    return type;
  }
}