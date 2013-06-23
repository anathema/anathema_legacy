package net.sf.anathema.initialization;

import net.sf.anathema.framework.module.IItemTypeConfiguration;

import java.util.ArrayList;
import java.util.Collection;

public class ItemTypeConfigurationCollection {

  private final Collection<IItemTypeConfiguration> itemTypeConfigurations = new ArrayList<>();

  public ItemTypeConfigurationCollection(ObjectFactory objectFactory)
          throws InitializationException {
    Collection<IItemTypeConfiguration> configurations = objectFactory.instantiateOrdered(ItemTypeConfiguration.class);
    itemTypeConfigurations.addAll(configurations);
  }

  public Collection<IItemTypeConfiguration> getItemTypes() {
    return new ArrayList<>(itemTypeConfigurations);
  }
}