package net.sf.anathema.character.reporting.pageformat;

import net.disy.commons.core.geometry.SmartRectangle;

public class PdfPageConfiguration {

  private int columnSpacing = 10;
  private int pageWidth = 595;
  private int pageHeight = 842;
  private int marginLeft = 40;
  private int marginRight = marginLeft;
  private int marginBottom = 15;
  private int marginTop = marginBottom;

  public int getContentWidth() {
    return pageWidth - marginLeft - marginRight;
  }

  public int getContentHeight() {
    return pageHeight - marginBottom - marginTop;
  }

  public int getColumnWidth() {
    return getColumnWidth(1);
  }

  public int getColumnWidth(int columnCount) {
    int oneColumnWidth = (getContentWidth() - 2 * columnSpacing) / 3;
    return (oneColumnWidth * columnCount) + (columnSpacing * (columnCount - 1));
  }

  public int getLeftColumnX(int columnIndex) {
    return columnIndex * (getColumnWidth() + columnSpacing) + marginLeft;
  }

  public int getUpperContentY() {
    return pageHeight - marginTop;
  }

  public SmartRectangle getFirstColumnRectangle(int spaceFromTop, int height, int columnCount) {
    return getColumnRectangle(spaceFromTop, height, columnCount, getLeftColumnX(0));
  }

  private SmartRectangle getColumnRectangle(int spaceFromTop, int height, int columnCount, int leftColumnX) {
    return new SmartRectangle(
        leftColumnX,
        (getUpperContentY() - spaceFromTop) - height,
        getColumnWidth(columnCount),
        height);
  }

  public SmartRectangle getSecondColumnRectangle(int spaceFromTop, int height, int columnCount) {
    return getColumnRectangle(spaceFromTop, height, columnCount, getLeftColumnX(1));
  }

  public SmartRectangle getThirdColumnRectangle(int spaceFromTop, int height) {
    return getColumnRectangle(spaceFromTop, height, 1, getLeftColumnX(2));
  }
}