package net.sf.anathema.framework.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.repository.ItemType;

public class ItemTypeRegistry implements IItemTypeRegistry {

  private final List<IItemType> itemTypes = new ArrayList<IItemType>();

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
    // todo ensures
    itemTypes.add(type);
  }

  @Override
  public IItemType[] getAllItemTypes() {
    return itemTypes.toArray(new ItemType[itemTypes.size()]);
  }
}