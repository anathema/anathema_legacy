package net.sf.anathema.platform.tree.presenter.view;

public interface ContentFactory {
  <T> T create(Object... parameters);
}