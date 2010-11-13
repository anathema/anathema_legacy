package net.sf.anathema.character.equipment.item.model;

import java.util.List;
import java.util.Map;

public interface ICollectionFactory {

  public <T> List<T> createList();

  public <K, V> Map<K, V> createHashMap();
}