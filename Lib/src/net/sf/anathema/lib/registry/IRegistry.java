package net.sf.anathema.lib.registry;

public interface IRegistry<I, V> {

  void register(I id, V anObject);

  V get(I id);
}