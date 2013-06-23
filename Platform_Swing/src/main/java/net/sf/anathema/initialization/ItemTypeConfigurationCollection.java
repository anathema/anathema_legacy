package net.sf.anathema.initialization;

import java.util.ArrayList;
import java.util.Collection;

public class ItemTypeConfigurationCollection {

  private final Collection<net.sf.anathema.framework.module.ItemTypeConfiguration> itemTypeConfigurations = new ArrayList<>();

  public ItemTypeConfigurationCollection(ObjectFactory objectFactory)
          throws InitializationException {
    Collection<net.sf.anathema.framework.module.ItemTypeConfiguration> configurations = objectFactory.instantiateOrdered(net.sf.anathema.initialization.ItemTypeConfiguration.class);
    itemTypeConfigurations.addAll(configurations);
  }

  public Collection<net.sf.anathema.framework.module.ItemTypeConfiguration> getItemTypes() {
    return new ArrayList<>(itemTypeConfigurations);
  }
}