package net.sf.anathema.lib.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiEntryMap<K, V> {

  public static <K, V> MultiEntryMap<K, V> convertMap(Map<K, Collection<V>> map) {
    MultiEntryMap<K, V> multiEntryMap = new MultiEntryMap<K, V>();
    for (K key : map.keySet()) {
      Collection<V> valueCollection = map.get(key);
      for (V value : valueCollection) {
        multiEntryMap.add(key, value);
      }
    }
    return multiEntryMap;
  }

  private Map<K, List<V>> map = new HashMap<K, List<V>>();

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

  public boolean containsKey(K key) {
    return map.containsKey(key);
  }

  public boolean containsValue(V value) {
    for (K key : keySet()) {
      List<V> list = getList(key);
      if (list.contains(value)) {
        return true;
      }
    }
    return false;
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

  public int size(K key) {
    if (!containsKey(key)) {
      return 0;
    }
    return getList(key).size();
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