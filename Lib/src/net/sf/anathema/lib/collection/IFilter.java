package net.sf.anathema.lib.collection;

public interface IFilter<P> {

  public boolean accept(P object);
}