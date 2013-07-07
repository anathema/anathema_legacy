package net.sf.anathema.hero.sheet.pdf.page;

public class Offset {

  private int columnOffset;

  public Offset(int columnOffset) {
    this.columnOffset = columnOffset;
  }

  public int value() {
    return columnOffset;
  }
}
