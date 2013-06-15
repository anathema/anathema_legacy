package net.sf.anathema.lib.registry;

import net.sf.anathema.lib.util.Identifier;

import java.util.Collection;

public interface IIdentificateRegistry<E extends Identifier> {

  void add(E... elements);

  Collection<E> getAll();

  boolean idRegistered(String id);

  E getById(String id);
}