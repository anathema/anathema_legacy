package net.sf.anathema.character.reporting.stats.anima;

public class ColumnDescriptor {

  private final String headerKey;
  private final float widthPart;

  public ColumnDescriptor(float widthPart, String headerKey) {
    this.widthPart = widthPart;
    this.headerKey = headerKey;
  }

  public String getHeaderKey() {
    return headerKey;
  }

  public float getWidthPart() {
    return widthPart;
  }
}