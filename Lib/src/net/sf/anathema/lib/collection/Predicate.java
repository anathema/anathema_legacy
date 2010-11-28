package net.sf.anathema.lib.collection;

import java.util.Collection;

import net.disy.commons.core.predicate.IPredicate;

public abstract class Predicate<T> implements IPredicate<T> {

  public T find(Collection<T> collection) {
    for (T t : collection) {
      if (evaluate(t)) {
        return t;
      }
    }
    return null;
  }
}