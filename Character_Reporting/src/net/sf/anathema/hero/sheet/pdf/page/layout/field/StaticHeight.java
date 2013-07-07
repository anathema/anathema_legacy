package net.sf.anathema.hero.sheet.pdf.page.layout.field;

public class StaticHeight implements HeightStrategy {

  private float height;

  public StaticHeight(float height) {
    this.height = height;
  }

  @Override
  public float getHeight(float contentWidth) {
    return height;
  }
}
