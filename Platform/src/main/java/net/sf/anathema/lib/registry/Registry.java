package net.sf.anathema.lib.registry;

import java.util.HashMap;
import java.util.Map;

public class Registry<I, V> implements IRegistry<I, V> {

  private final Map<I, V> objects = new HashMap<>();
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
  }

  @Override
  public V get(I id) {
    if (defaultValue != null && !objects.containsKey(id)) {
      return defaultValue;
    }
    return objects.get(id);
  }
}