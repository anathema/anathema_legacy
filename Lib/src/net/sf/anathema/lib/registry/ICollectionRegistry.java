package net.sf.anathema.lib.registry;

import java.util.Collection;

public interface ICollectionRegistry<E> {

  void add(E... elements);

  Collection<E> getAll();
}