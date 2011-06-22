package net.sf.anathema.character.reporting.sheet.pageformat;

import java.awt.Dimension;

import net.sf.anathema.character.reporting.util.Bounds;

import com.lowagie.text.Rectangle;

public class PdfPageConfiguration {

  public static final int COLUMN_SPACING = 10;
  public static final int HORIZONTAL_MARGIN = 25;
  public static final int VERTICAL_MARGIN = 15;

  public static PdfPageConfiguration create(Rectangle pageSize) {
    return new PdfPageConfiguration(new Dimension((int) pageSize.width(), (int) pageSize.height()), VERTICAL_MARGIN, HORIZONTAL_MARGIN);
  }
  
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

  private Bounds getColumnRectangle(float spaceFromTop, float height, int columnCount, float leftColumnX) {
    return new Bounds(leftColumnX, getUpperContentY() - spaceFromTop - height, getColumnWidth(columnCount), height);
  }

  public float getColumnWidth() {
    return getColumnWidth(1);
  }

  public float getColumnWidth(int columnCount) {
    float oneColumnWidth = (getContentWidth() - 2 * COLUMN_SPACING) / 3;
    return oneColumnWidth * columnCount + COLUMN_SPACING * (columnCount - 1);
  }

  public float getContentHeight() {
    return pageHeight - marginBottom - marginTop;
  }

  public float getContentWidth() {
    return pageWidth - marginLeft - marginRight;
  }

  public float getLowerContentY() {
    return marginBottom;
  }

  public Bounds getFirstColumnRectangle(float spaceFromTop, float height, int columnCount) {
    return getColumnRectangle(spaceFromTop, height, columnCount, getLeftColumnX(0));
  }

  public float getLeftColumnX(int columnIndex) {
    return columnIndex * (getColumnWidth() + COLUMN_SPACING) + marginLeft;
  }

  public Bounds getSecondColumnRectangle(float spaceFromTop, float height, int columnCount) {
    return getColumnRectangle(spaceFromTop, height, columnCount, getLeftColumnX(1));
  }

  public Bounds getThirdColumnRectangle(float spaceFromTop, float height) {
    return getColumnRectangle(spaceFromTop, height, 1, getLeftColumnX(2));
  }

  public int getUpperContentY() {
    return pageHeight - marginTop;
  }

  public float getLeftX() {
    return marginLeft;
  }

  public int getPageHeight() {
    return pageHeight;
  }
}