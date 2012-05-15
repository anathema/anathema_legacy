package net.sf.anathema.lib.gui.icon;

import java.awt.image.RGBImageFilter;

public class DisabledFilter extends RGBImageFilter {

  private final int redAddend;
  private final double redDividend;
  private final int greenAddend;
  private final double greenDividend;
  private final int blueAddend;
  private final double blueDividend;

  public DisabledFilter() {
    this(128, 2.0);
  }

  public DisabledFilter(int addend, double dividend) {
    this(addend, dividend, addend, dividend, addend, dividend);
  }

  public DisabledFilter(int redAddend, double redDividend, int greenAddend, double greenDividend, int blueAddend, double blueDividend) {
    this.redAddend = redAddend;
    this.redDividend = redDividend;
    this.greenAddend = greenAddend;
    this.greenDividend = greenDividend;
    this.blueAddend = blueAddend;
    this.blueDividend = blueDividend;
  }

  @Override
  public int filterRGB(int x, int y, int rgb) {
    int r = (int) ((((rgb & 0xff0000) >> 16) + redAddend) / redDividend);
    int g = (int) ((((rgb & 0x00ff00) >> 8) + greenAddend) / greenDividend);
    int b = (int) (((rgb & 0x0000ff) + blueAddend) / blueDividend);
    return ((rgb & 0xff000000) | (r << 16) | (g << 8) | b);
  }
}