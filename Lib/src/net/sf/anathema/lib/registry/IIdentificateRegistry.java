package net.sf.anathema.lib.registry;

import net.sf.anathema.lib.util.IIdentificate;

public interface IIdentificateRegistry<E extends IIdentificate> extends ICollectionRegistry<E> {

  boolean idRegistered(String id);

  E getById(String id);
}