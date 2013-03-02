package net.sf.anathema.cascades.module;

import net.sf.anathema.framework.module.AbstractNonPersistableItemTypeConfiguration;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.initialization.ItemTypeConfiguration;
import net.sf.anathema.initialization.reflections.Weight;

@ItemTypeConfiguration
@Weight(weight = 0)
public final class CharmCascadeItemTypeConfiguration extends AbstractNonPersistableItemTypeConfiguration {

  public static final String CHARM_CASCADES_ITEM_TYPE_ID = "CharmCascades"; //$NON-NLS-1$

  public CharmCascadeItemTypeConfiguration() {
    super(new ItemType(CHARM_CASCADES_ITEM_TYPE_ID, null));
  }
}
