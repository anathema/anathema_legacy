package net.sf.anathema.lib.awt;

import java.awt.Dimension;

public class Dimensions {

  public static Dimension maximize(Dimension first, Dimension second) {
    int width = Math.max(first.width, second.width);
    int height = Math.max(first.height, second.height);
    return new Dimension(width, height);
  }

  public static Dimension minimize(Dimension first, Dimension second) {
    int width = Math.min(first.width, second.width);
    int height = Math.min(first.height, second.height);
    return new Dimension(width, height);
  }

}