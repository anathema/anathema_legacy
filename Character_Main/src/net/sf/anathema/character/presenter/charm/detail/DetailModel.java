package net.sf.anathema.character.presenter.charm.detail;

public interface DetailModel<T> {
  
  boolean isActive();
  
  void setDetailFor(T object);
}
