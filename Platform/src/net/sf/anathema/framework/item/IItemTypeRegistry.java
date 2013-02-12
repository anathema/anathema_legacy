package net.sf.anathema.framework.item;

import java.util.Collection;

public interface IItemTypeRegistry {

  IItemType getById(String id);

  void registerItemType(IItemType type);

  IItemType[] getAllItemTypes();

  Collection<IItemType> getIntegratedItemTypes();
}