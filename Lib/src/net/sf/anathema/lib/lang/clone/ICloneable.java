package net.sf.anathema.lib.lang.clone;

public interface ICloneable<C extends Cloneable> extends Cloneable {

  public C clone();
}