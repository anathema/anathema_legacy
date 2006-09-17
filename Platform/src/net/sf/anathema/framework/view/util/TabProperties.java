package net.sf.anathema.framework.view.util;

public class TabProperties {

  private final String name;
  private boolean needsScrollbar = false;

  public TabProperties(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public boolean isNeedsScrollbar() {
    return needsScrollbar;
  }

  public TabProperties setNeedsScrollbar(boolean needsScrollbar) {
    this.needsScrollbar = needsScrollbar;
    return this;
  }
}
