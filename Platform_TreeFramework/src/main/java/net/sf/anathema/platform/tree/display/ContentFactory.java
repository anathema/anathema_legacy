package net.sf.anathema.platform.tree.display;

public interface ContentFactory {
  <T> T create(Object... parameters);
}