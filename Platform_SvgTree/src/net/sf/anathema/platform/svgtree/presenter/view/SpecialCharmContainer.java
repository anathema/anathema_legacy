package net.sf.anathema.platform.svgtree.presenter.view;

public interface SpecialCharmContainer {
  <T> T add(Class<T> contentType, Object... parameters);
}