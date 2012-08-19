package net.sf.anathema.platform.tree.presenter.view;

public interface SpecialCharmContainer {
  <T> T add(Class<T> contentType, Object... parameters);
}