package net.sf.anathema.initialization;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.module.ItemTypeConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ItemTypeCollection implements Iterable<IItemType> {

  private final Collection<ItemTypeConfiguration> itemTypeConfigurations = new ArrayList<>();

  public ItemTypeCollection(ObjectFactory objectFactory) throws InitializationException {
    Collection<ItemTypeConfiguration> configurations = objectFactory.instantiateOrdered(RegisteredItemTypeConfiguration.class);
    itemTypeConfigurations.addAll(configurations);
  }

  public IItemType getById(String id){
    for (IItemType itemType : getAllTypes()) {
      if (itemType.getId().equals(id)) {
        return itemType;
      }
    }
    throw new IllegalArgumentException("No item type registered for id: " + id);
  }

  public Collection<IItemType> getAllTypes() {
    return Collections2.transform(itemTypeConfigurations, new Function<ItemTypeConfiguration, IItemType>() {
      @Override
      public IItemType apply(ItemTypeConfiguration input) {
        return input.getItemType();
      }
    });
  }

  @Override
  public Iterator<IItemType> iterator() {
    return getAllTypes().iterator();
  }
}