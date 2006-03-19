package net.sf.anathema.lib.awt;

import java.awt.Point;

public class Points {

  private static boolean neighborInts(int first, int second) {
    return Math.abs(first - second) == 1;
  }

  public static boolean neighbors(Point first, Point second) {
    return neighborInts(first.x, second.x) || neighborInts(first.y, second.y);
  }

  public static Point add(Point first, int number) {
    return add(first, new Point(number, number));
  }

  public static Point add(Point first, int addX, int addY) {
    return add(first, new Point(addX, addY));
  }

  public static Point add(Point first, Point second) {
    return new Point(first.x + second.x, first.y + second.y);
  }

  public static double getDistance(Point first, Point second) {
    final int deltaX = first.x - second.x;
    final int deltaY = first.y - second.y;
    return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
  }

}