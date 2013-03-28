package net.sf.anathema.framework.model;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;

import java.util.ArrayList;
import java.util.List;

public class ItemTypeRegistry implements IItemTypeRegistry {

  private final List<IItemType> itemTypes = new ArrayList<>();

  @Override
  public IItemType getById(String id) {
    for (IItemType itemType : itemTypes) {
      if (itemType.getId().equals(id)) {
        return itemType;
      }
    }
    throw new IllegalArgumentException("No item type registered for id: " + id); //$NON-NLS-1$
  }

  @Override
  public void registerItemType(IItemType type) {
    itemTypes.add(type);
  }

  @Override
  public IItemType[] getAllItemTypes() {
    return itemTypes.toArray(new IItemType[itemTypes.size()]);
  }
}