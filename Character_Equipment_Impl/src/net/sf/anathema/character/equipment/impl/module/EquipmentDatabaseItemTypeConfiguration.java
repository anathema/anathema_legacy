package net.sf.anathema.character.equipment.impl.module;

import net.sf.anathema.framework.module.AbstractNonPersistableItemTypeConfiguration;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.initialization.ItemTypeConfiguration;
import net.sf.anathema.initialization.reflections.Weight;

@ItemTypeConfiguration
@Weight(weight = 10)
public final class EquipmentDatabaseItemTypeConfiguration extends AbstractNonPersistableItemTypeConfiguration {

  public static final String EQUIPMENT_DATABASE_ITEM_TYPE_ID = "EquipmentDatabase";
  private static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new RepositoryConfiguration(".item", "equipment/");


  public EquipmentDatabaseItemTypeConfiguration() {
    super(new ItemType(EQUIPMENT_DATABASE_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION, false));
  }
}