package net.sf.anathema.framework.ui;

public class Coordinate {

  public final double x;
  public final double y;

  public Coordinate() {
    this(0, 0);
  }

  public Coordinate(int x, int y) {
    this((double) x, (double) y);
  }

  public Coordinate(double x, double y) {
    this.x = x;
    this.y = y;
  }
}