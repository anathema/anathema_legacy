package net.sf.anathema.framework.ui;

public class Area {

  public final int height;
  public final int width;

  public Area(int width, int height) {
    this.height = height;
    this.width = width;
  }

  public Area() {
    this(0, 0);
  }
}