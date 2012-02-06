package net.sf.anathema.character.reporting.pdf.layout.field;

public class StaticHeight implements HeightStrategy {

  private float height;

  public StaticHeight(float height) {
    this.height = height;
  }

  @Override
  public float getHeight(float layoutWidth) {
    return height;
  }
}
