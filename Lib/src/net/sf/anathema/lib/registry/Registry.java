package net.sf.anathema.lib.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.anathema.lib.collection.ListOrderedSet;

public class Registry<I, V> implements IRegistry<I, V> {

  private final Map<I, V> objects = new HashMap<I, V>();
  private final Set<I> keySet = new ListOrderedSet<I>();
  private V defaultValue;

  public Registry() {
    this(null);
  }

  public Registry(V defaultValue) {
    this.defaultValue = defaultValue;
  }

  public void register(I id, V anObject) {
    objects.put(id, anObject);
    keySet.add(id);
  }

  public V get(I id) {
    if (defaultValue != null && !objects.containsKey(id)) {
      return defaultValue;
    }
    return objects.get(id);
  }

  public I[] getIds(I[] array) {
    return keySet.toArray(array);
  }

  public void setDefaultValue(V defaultValue) {
    this.defaultValue = defaultValue;
  }

  protected Set<I> getKeys() {
    return keySet;
  }
}