package net.sf.anathema.framework.view.util;

public class ContentProperties {

  private final String name;
  private boolean needsScrollBar = false;

  public ContentProperties(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public boolean isScrollBarNeeded() {
    return needsScrollBar;
  }

  public ContentProperties needsScrollBar() {
    this.needsScrollBar = true;
    return this;
  }
}