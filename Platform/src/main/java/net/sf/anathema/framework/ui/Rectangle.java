package net.sf.anathema.framework.ui;

public class Rectangle {

  public final Coordinate origin;
  public final Area area;

  public Rectangle(Coordinate origin, Area area) {
    this.origin = origin;
    this.area = area;
  }
}