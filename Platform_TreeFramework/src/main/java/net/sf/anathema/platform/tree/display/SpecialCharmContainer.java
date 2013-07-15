package net.sf.anathema.platform.tree.display;

public interface SpecialCharmContainer {
  <T> T add(Class<T> contentType, Object... parameters);
}