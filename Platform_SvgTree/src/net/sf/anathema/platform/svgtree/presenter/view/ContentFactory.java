package net.sf.anathema.platform.svgtree.presenter.view;

public interface ContentFactory {
  <T> T create(Object... parameters);
}