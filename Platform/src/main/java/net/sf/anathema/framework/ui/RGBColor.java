package net.sf.anathema.framework.ui;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RGBColor {

  public final int red;
  public final int green;
  public final int blue;
  public final int alpha;

  public RGBColor(int red, int green, int blue) {
    this(red, green, blue, 255);
  }

  public RGBColor(int red, int green, int blue, int alpha) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
  }

  public RGBColor(RGBColor color) {
    this.red = color.red;
    this.green = color.green;
    this.blue = color.blue;
    this.alpha = color.alpha;
  }

  public RGBColor(RGBColor color, int newAlpha) {
    this.red = color.red;
    this.green = color.green;
    this.blue = color.blue;
    this.alpha = newAlpha;
  }


  public static final RGBColor White = new RGBColor(255, 255, 255);
  public static final RGBColor Pink = new RGBColor(255, 175, 175);
  public static final RGBColor Red = new RGBColor(255, 0, 0);
  public static final RGBColor Blue = new RGBColor(0, 0, 255);
  public static final RGBColor Black = new RGBColor(0, 0, 0);
  public static final RGBColor Transparent = new RGBColor(0, 0, 0, 0);

  private static final double FACTOR = 0.7;

  public RGBColor brighter() {
    int faintColorValue = (int) (1.0 / (1.0 - FACTOR));
    if (isBlack()) {
      return new RGBColor(faintColorValue, faintColorValue, faintColorValue, alpha);
    }
    int protoRed = preBrightenIfDark(faintColorValue, red);
    int protoGreen = preBrightenIfDark(faintColorValue, green);
    int protoBlue = preBrightenIfDark(faintColorValue, blue);
    int brighterRed = brighten(protoRed);
    int brighterGreen = brighten(protoGreen);
    int brighterBlue = brighten(protoBlue);
    return new RGBColor(brighterRed, brighterGreen, brighterBlue, alpha);
  }

  private int brighten(int newRed) {
    return Math.min((int) (newRed / FACTOR), 255);
  }

  private boolean isBlack() {
    return red == 0 && green == 0 && blue == 0;
  }

  private int preBrightenIfDark(int i, int color) {
    if (color > 0 && color < i) {
      return i;
    }
    return color;
  }

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}