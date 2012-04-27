package net.sf.anathema.lib.registry;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class CollectionRegistry<E> implements ICollectionRegistry<E> {

  private final Set<E> elements = new LinkedHashSet<E>();

  @Override
  public void add(E... newElements) {
    for (E element : newElements) {
      elements.add(element);
    }
  }

  @Override
  public final Collection<E> getAll() {
    return elements;
  }
}