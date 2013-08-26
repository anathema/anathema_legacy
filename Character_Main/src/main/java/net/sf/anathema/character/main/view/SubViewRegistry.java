package net.sf.anathema.character.main.view;

public interface SubViewRegistry {
  <T> T get(Class<T> viewClass);
}