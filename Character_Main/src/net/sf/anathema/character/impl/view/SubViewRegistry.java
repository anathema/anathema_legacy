package net.sf.anathema.character.impl.view;

public interface SubViewRegistry {
  <T> T get(Class<T> viewClass);
}