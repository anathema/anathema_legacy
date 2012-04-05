package net.sf.anathema.character.equipment.impl.item.model.gson;

import net.sf.anathema.character.equipment.item.model.ICollectionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonCollectionFactory implements ICollectionFactory {
  @Override
  public <T> List<T> createList() {
    return new ArrayList<T>();
  }

  @Override
  public <K, V> Map<K, V> createHashMap() {
    return new HashMap<K, V>();
  }
}