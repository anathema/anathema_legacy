package net.sf.anathema.lib.registry;

import java.util.Collection;
import java.util.Set;

import net.sf.anathema.lib.collection.ListOrderedSet;

public class CollectionRegistry<E> implements ICollectionRegistry<E> {

  private final Set<E> elements = new ListOrderedSet<E>();

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