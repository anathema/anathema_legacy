package net.sf.anathema.framework.item;

public interface IItemTypeRegistry {

  public IItemType getById(String id);

  public void registerItemType(IItemType type);

  public IItemType[] getAllItemTypes();
}