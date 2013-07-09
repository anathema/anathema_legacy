package net.sf.anathema.initialization;

import net.sf.anathema.framework.module.ItemTypeConfiguration;

import java.util.ArrayList;
import java.util.Collection;

public class ItemTypeConfigurationCollection {

  private final Collection<ItemTypeConfiguration> itemTypeConfigurations = new ArrayList<>();

  public ItemTypeConfigurationCollection(ObjectFactory objectFactory) throws InitializationException {
    Collection<ItemTypeConfiguration> configurations = objectFactory.instantiateOrdered(RegisteredItemTypeConfiguration.class);
    itemTypeConfigurations.addAll(configurations);
  }

  public Collection<ItemTypeConfiguration> getItemTypes() {
    return new ArrayList<>(itemTypeConfigurations);
  }
}