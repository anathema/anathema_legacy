package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;

import java.util.HashMap;
import java.util.Map;

public class ItemTypePropertiesMap {
  private final Map<IItemType, IItemTypeViewProperties> map = new HashMap<>();

  public void put(IItemType type, IItemTypeViewProperties properties) {
    map.put(type, properties);
  }

  public IItemTypeViewProperties get(IItemType itemType) {
    return map.get(itemType);
  }
}
