package net.sf.anathema.character.reporting.pdf.rendering.extent;

public class Bounds {

  public final float x;
  public final float y;
  public final float width;
  public final float height;

  public Bounds(float x, float y, float width, float height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public float getCenterX() {
    return x + width / 2;
  }

  public float getCenterY() {
    return y + height / 2;
  }

  public float getHeight() {
    return height;
  }

  public float getMaxX() {
    return x + width;
  }

  public float getMaxY() {
    return y + height;
  }

  public float getMinX() {
    return x;
  }

  public float getMinY() {
    return y;
  }

  public float getWidth() {
    return width;
  }

  @Override
  public String toString() {
    return getClass().getName() + "[" + x + "," + y + "," + width + "," + height + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    // //$NON-NLS-5$
  }
}
