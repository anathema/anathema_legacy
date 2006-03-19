package net.sf.anathema.lib.collection;

import java.util.Collection;

public abstract class Predicate<T> {
  public abstract boolean evaluate(T t);

  public T find(Collection<T> collection) {
    for (T t : collection) {
      if (evaluate(t)) {
        return t;
      }
    }
    return null;
  }
}