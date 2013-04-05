package net.sf.anathema.lib.registry;

import net.sf.anathema.lib.util.Identified;

import java.util.Collection;

public interface IIdentificateRegistry<E extends Identified> {

  void add(E... elements);

  Collection<E> getAll();

  boolean idRegistered(String id);

  E getById(String id);
}