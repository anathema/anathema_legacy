package net.sf.anathema.lib.registry;

public interface IRegistry<I, V> {

  public void register(I id, V anObject);

  public V get(I id);

  public I[] getIds(I[] array);
}