package net.sf.anathema.framework.view.util;

public class ContentProperties {

  private final String name;
  private boolean needsScrollbar = false;

  public ContentProperties(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public boolean isNeedsScrollbar() {
    return needsScrollbar;
  }

  public ContentProperties needsScrollbar() {
    this.needsScrollbar = true;
    return this;
  }
}