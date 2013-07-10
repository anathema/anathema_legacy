package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.lib.util.Identifier;

import java.util.Collection;

public interface IIdentificateRegistry<E extends Identifier> {

  void add(E... elements);

  Collection<E> getAll();

  boolean idRegistered(String id);

  E getById(String id);
}