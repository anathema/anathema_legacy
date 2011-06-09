package net.sf.anathema.lib.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiEntryMap<K, V> {

  private final Map<K, List<V>> map = new HashMap<K, List<V>>();

  public void add(K key, V... value) {
    List<V> list;
    if (!containsKey(key)) {
      list = new ArrayList<V>();
      map.put(key, list);
    }
    else {
      list = getList(key);
    }
    Collections.addAll(list, value);
  }

  @SuppressWarnings("unchecked")
  public void replace(K key, V oldValue, V newValue) {
    if (!containsKey(key)) {
      add(key, newValue);
      return;
    }
    List<V> list = getList(key);
    if (list.contains(oldValue)) {
      int index = list.indexOf(oldValue);
      list.remove(index);
      list.add(index, newValue);
    }
    else {
      list.add(newValue);
    }
  }

  public boolean containsKey(K key) {
    return map.containsKey(key);
  }

  public List<V> get(K key) {
    if (!containsKey(key)) {
      return Collections.emptyList();
    }
    return Collections.unmodifiableList(getList(key));
  }

  private List<V> getList(K key) {
    return map.get(key);
  }

  public Set<K> keySet() {
    return Collections.unmodifiableSet(map.keySet());
  }

  public void removeValue(K key, V value) {
    if (!containsKey(key)) {
      return;
    }
    getList(key).remove(value);
  }

  public void clear() {
    map.clear();
  }
}