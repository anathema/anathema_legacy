package net.sf.anathema.lib.registry;

import net.sf.anathema.lib.util.IIdentificate;

public interface IIdentificateRegistry<E extends IIdentificate> extends ICollectionRegistry<E> {

  public boolean idRegistered(String id);

  public E getById(String id);
}