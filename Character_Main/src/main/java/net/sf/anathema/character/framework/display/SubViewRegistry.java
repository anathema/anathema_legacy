package net.sf.anathema.character.framework.display;

public interface SubViewRegistry {
  <T> T get(Class<T> viewClass);
}