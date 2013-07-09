package net.sf.anathema.character.equipment.module;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.initialization.RegisteredItemTypeConfiguration;

@RegisteredItemTypeConfiguration
public class EquipmentDatabaseItemTypeConfiguration implements net.sf.anathema.framework.module.ItemTypeConfiguration {

  private static final String EQUIPMENT_DATABASE_ITEM_TYPE_ID = "EquipmentDatabase";
  private static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new RepositoryConfiguration(".item", "equipment/");
  private final IItemType type;

  public EquipmentDatabaseItemTypeConfiguration() {
    this.type = new ItemType(EQUIPMENT_DATABASE_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION, false);
  }

  @Override
  public IItemType getItemType() {
    return type;
  }
}