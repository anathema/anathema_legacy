package net.sf.anathema.character.reporting.sheet.pageformat;

import java.awt.Dimension;

import net.disy.commons.core.geometry.SmartRectangle;

import com.lowagie.text.Rectangle;

public class PdfPageConfiguration {

  public static PdfPageConfiguration create(Rectangle pageSize) {
    return new PdfPageConfiguration(new Dimension((int) pageSize.width(), (int) pageSize.height()), 15, 40);
  }

  private int columnSpacing = 10;
  private int pageWidth;
  private int pageHeight;
  private int marginLeft;
  private int marginRight;

  private int marginBottom;

  private int marginTop;

  private PdfPageConfiguration(Dimension pageSize, int verticalMargin, int horizontalMargin) {
    this.pageWidth = pageSize.width;
    this.pageHeight = pageSize.height;
    this.marginLeft = horizontalMargin;
    this.marginRight = horizontalMargin;
    this.marginTop = verticalMargin;
    this.marginBottom = verticalMargin;
  }

  private SmartRectangle getColumnRectangle(int spaceFromTop, int height, int columnCount, int leftColumnX) {
    return new SmartRectangle(
        leftColumnX,
        (getUpperContentY() - spaceFromTop) - height,
        getColumnWidth(columnCount),
        height);
  }

  public int getColumnWidth() {
    return getColumnWidth(1);
  }

  public int getColumnWidth(int columnCount) {
    int oneColumnWidth = (getContentWidth() - 2 * columnSpacing) / 3;
    return (oneColumnWidth * columnCount) + (columnSpacing * (columnCount - 1));
  }

  public int getContentHeight() {
    return pageHeight - marginBottom - marginTop;
  }

  public int getContentWidth() {
    return pageWidth - marginLeft - marginRight;
  }

  public SmartRectangle getFirstColumnRectangle(int spaceFromTop, int height, int columnCount) {
    return getColumnRectangle(spaceFromTop, height, columnCount, getLeftColumnX(0));
  }

  public int getLeftColumnX(int columnIndex) {
    return columnIndex * (getColumnWidth() + columnSpacing) + marginLeft;
  }

  public SmartRectangle getSecondColumnRectangle(int spaceFromTop, int height, int columnCount) {
    return getColumnRectangle(spaceFromTop, height, columnCount, getLeftColumnX(1));
  }

  public SmartRectangle getThirdColumnRectangle(int spaceFromTop, int height) {
    return getColumnRectangle(spaceFromTop, height, 1, getLeftColumnX(2));
  }

  public int getUpperContentY() {
    return pageHeight - marginTop;
  }
}