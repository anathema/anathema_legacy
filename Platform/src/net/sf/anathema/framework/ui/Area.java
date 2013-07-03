package net.sf.anathema.framework.ui;

public class Area {

  public final int height;
  public final int width;

  public Area() {
    this(0, 0);
  }

  public Area(int width, int height) {
    this.height = height;
    this.width = width;
  }

  public Area(double width, double height) {
    this.height = (int) height;
    this.width = (int) width;
  }
}