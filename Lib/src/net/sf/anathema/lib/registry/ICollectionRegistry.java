package net.sf.anathema.lib.registry;

import java.util.Collection;

public interface ICollectionRegistry<E> {

  public void add(E... elements);

  public Collection<E> getAll();
}