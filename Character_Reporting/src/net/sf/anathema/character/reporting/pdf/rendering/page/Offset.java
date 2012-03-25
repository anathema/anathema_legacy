package net.sf.anathema.character.reporting.pdf.rendering.page;

public class Offset {

  private int columnOffset;

  public Offset(int columnOffset) {
    this.columnOffset = columnOffset;
  }

  public int value() {
    return columnOffset;
  }
}
