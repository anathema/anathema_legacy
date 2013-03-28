package net.sf.anathema.framework.item;

public interface IItemTypeRegistry {

  IItemType getById(String id);

  void registerItemType(IItemType type);

  IItemType[] getAllItemTypes();
}