package net.sf.anathema.character.reporting.pdf.rendering.page;

public class Margins {
  
  public static Margins ForPortraitSheet() {
    int horizontal = 20;
    int top = 15;
    int bottom = 22;
    return new Margins(top, bottom, horizontal, horizontal);
  }
  
  public static Margins ForLandscapeSheet() {
    return new Margins(10, 10, 10, 10);
  }
  
  public final float top;
  public final float bottom;
  public final float left;
  public final float right;

  private Margins(float top, float bottom, float left, float right) {
    this.top = top;
    this.bottom = bottom;
    this.right = right;
    this.left = left;
  }
}
