package net.sf.anathema.lib.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Table<K1, K2, V> {

  private final List<K1> primaryKeys = new ArrayList<K1>();
  private final List<K2> secondaryKeys = new ArrayList<K2>();
  private final List<List<V>> rowMap = new ArrayList<List<V>>();
  private final List<List<V>> columnMap = new ArrayList<List<V>>();

  public void add(K1 key1, K2 key2, V value) {
    if (!primaryKeys.contains(key1)) {
      primaryKeys.add(key1);
      rowMap.add(new ArrayList<V>());

    }
    if (!secondaryKeys.contains(key2)) {
      secondaryKeys.add(key2);
      columnMap.add(new ArrayList<V>());
    }
    int primaryIndex = primaryKeys.indexOf(key1);
    int secondaryIndex = secondaryKeys.indexOf(key2);
    addToList(rowMap.get(primaryIndex), value, secondaryIndex);
    addToList(columnMap.get(secondaryIndex), value, primaryIndex);
  }

  private void addToList(List<V> list, V value, int index) {
    if (index != -1 && index < list.size()) {
      list.remove(index);
      list.add(index, value);
      return;
    }
    for (int fillIndex = list.size(); fillIndex < index; fillIndex++) {
      list.add(null);
    }
    list.add(value);
  }

  public V get(K1 key1, K2 key2) {
    if (key1 == null || key2 == null) {
      return null;
    }
    int primaryIndex = primaryKeys.indexOf(key1);
    int secondaryIndex = secondaryKeys.indexOf(key2);
    if (primaryIndex == -1 || secondaryIndex == -1) {
      return null;
    }
    V rowValue = getFromListList(rowMap, primaryIndex, secondaryIndex);
    V columnValue = getFromListList(columnMap, secondaryIndex, primaryIndex);
    if (rowValue == null || columnValue == null || !rowValue.equals(columnValue)) {
      return null;
    }
    return rowValue;
  }

  private V getFromListList(List<List<V>> list, int primaryIndex, int secondaryIndex) {
    List<V> innerList = list.get(primaryIndex);
    if (secondaryIndex >= innerList.size()) {
      return null;
    }
    return innerList.get(secondaryIndex);
  }

  public Set<K1> getPrimaryKeys() {
    return new HashSet<K1>(primaryKeys);
  }

  public boolean contains(K1 primaryKey, K2 secondaryKey) {
    return get(primaryKey, secondaryKey) != null;
  }

  public int getSize() {
    return primaryKeys.size() * secondaryKeys.size();
  }
}