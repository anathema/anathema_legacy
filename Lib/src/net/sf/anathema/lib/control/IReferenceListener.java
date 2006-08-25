package net.sf.anathema.lib.control;

public interface IReferenceListener<R> {

  public void objectAdded(R object);

  public void objectRemoved(R object);
  
  public void objectChanged(R object);
}