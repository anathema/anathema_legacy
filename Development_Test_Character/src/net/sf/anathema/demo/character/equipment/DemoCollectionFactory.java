package net.sf.anathema.demo.character.equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.equipment.item.model.ICollectionFactory;

public class DemoCollectionFactory implements ICollectionFactory {

  public <K, V> Map<K, V> createHashMap() {
    return new HashMap<K, V>();
  }

  public <T> List<T> createList() {
    return new ArrayList<T>();
  }
}