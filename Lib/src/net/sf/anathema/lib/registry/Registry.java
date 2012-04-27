package net.sf.anathema.lib.registry;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Registry<I, V> implements IRegistry<I, V> {

  private final Map<I, V> objects = new HashMap<I, V>();
  private final Set<I> keySet = new LinkedHashSet<I>();
  private V defaultValue;

  public Registry() {
    this(null);
  }

  public Registry(V defaultValue) {
    this.defaultValue = defaultValue;
  }

  @Override
  public void register(I id, V anObject) {
    objects.put(id, anObject);
    keySet.add(id);
  }

  @Override
  public V get(I id) {
    if (defaultValue != null && !objects.containsKey(id)) {
      return defaultValue;
    }
    return objects.get(id);
  }

  @Override
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