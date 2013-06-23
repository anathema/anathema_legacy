package net.sf.anathema.cascades.module;

import net.sf.anathema.framework.module.AbstractNonPersistableItemTypeConfiguration;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.initialization.ItemTypeConfiguration;

@ItemTypeConfiguration
public class CharmCascadeItemTypeConfiguration extends AbstractNonPersistableItemTypeConfiguration {

  public static final String CHARM_CASCADES_ITEM_TYPE_ID = "CharmCascades";

  public CharmCascadeItemTypeConfiguration() {
    super(new ItemType(CHARM_CASCADES_ITEM_TYPE_ID, null));
  }
}