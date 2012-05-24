package net.sf.anathema.lib.registry;

import net.sf.anathema.lib.util.Identified;

public interface IIdentificateRegistry<E extends Identified> extends ICollectionRegistry<E> {

  boolean idRegistered(String id);

  E getById(String id);
}