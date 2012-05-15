package net.sf.anathema.character.equipment.item.model;

import java.util.List;
import java.util.Map;

public interface ICollectionFactory {

  <T> List<T> createList();

  <K, V> Map<K, V> createHashMap();
}