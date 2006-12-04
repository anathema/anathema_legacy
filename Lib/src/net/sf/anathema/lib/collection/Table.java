package net.sf.anathema.lib.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.lang.ArrayFactory;

public class Table<K1, K2, V> {

  private final MultiEntryMap<K1, V> rowMap = new MultiEntryMap<K1, V>();
  private final MultiEntryMap<K2, V> columnMap = new MultiEntryMap<K2, V>();
  private final Set<K1> primaryKeys = new ListOrderedSet<K1>();
  private final ArrayFactory<V> factory;

  public Table(Class<V> valueClass) {
    factory = new ArrayFactory<V>(valueClass);
  }

  public void add(K1 key1, K2 key2, V value) {
    rowMap.add(key1, value);
    columnMap.add(key2, value);
    primaryKeys.add(key1);
  }

  public V get(K1 key1, K2 key2) {
    List<V> rowList = rowMap.get(key1);
    Set<V> rowEntries = new HashSet<V>(rowList);
    List<V> columnList = columnMap.get(key2);
    Set<V> columnEntries = new HashSet<V>(columnList);
    rowEntries.retainAll(columnEntries);
    if (rowEntries.size() == 0) {
      return null;
    }
    Ensure.ensureArgumentEquals(1, rowEntries.size());
    V[] intersection = factory.createArray(1);
    return rowEntries.toArray(intersection)[0];
  }

  public Set<K1> getPrimaryKeys() {
    return Collections.unmodifiableSet(primaryKeys);
  }

  public boolean contains(K1 primaryKey, K2 secondaryKey) {
    return get(primaryKey, secondaryKey) != null;
  }

  public int getSize() {
    return 0;
  }
}