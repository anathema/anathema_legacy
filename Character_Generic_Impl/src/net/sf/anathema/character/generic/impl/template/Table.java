package net.sf.anathema.character.generic.impl.template;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.collection.MultiEntryMap;

public class Table<K1, K2, V> {

  private final MultiEntryMap<K1, V> rowMap = new MultiEntryMap<K1, V>();
  private final MultiEntryMap<K2, V> columnMap = new MultiEntryMap<K2, V>();

  public void add(K1 key1, K2 key2, V value) {
    rowMap.add(key1, value);
    columnMap.add(key2, value);
  }

  public V get(K1 key1, K2 key2) {
    List<V> rowList = rowMap.get(key1);
    if (rowList == null) {
      return null;
    }
    Set<V> rowEntries = new HashSet<V>(rowList);
    List<V> columnList = columnMap.get(key2);
    if (columnList == null) {
      return null;
    }
    Set<V> columnEntries = new HashSet<V>(columnList);
    rowEntries.retainAll(columnEntries);
    if (rowEntries.size() == 0) {
      return null;
    }
    Ensure.ensureArgumentEquals(1, rowEntries.size());
    return (V) rowEntries.toArray()[0];
  }

  public Set<K1> getPrimaryKeys() {
    return Collections.unmodifiableSet(rowMap.keySet());
  }
}