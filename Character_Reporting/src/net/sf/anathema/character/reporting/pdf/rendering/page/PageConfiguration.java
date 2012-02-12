package net.sf.anathema.character.reporting.pdf.rendering.page;

import com.itextpdf.text.Rectangle;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;

import java.awt.*;

public class PageConfiguration {

  public static final int COLUMN_SPACING = 10;
  public static final int HORIZONTAL_MARGIN = 20;
  public static final int TOP_MARGIN = 15;
  public static final int BOTTOM_MARGIN = 22;

  public static PageConfiguration create(Rectangle pageSize) {
    return new PageConfiguration(new Dimension((int) pageSize.getWidth(), (int) pageSize.getHeight()), TOP_MARGIN, BOTTOM_MARGIN, HORIZONTAL_MARGIN);
  }

  private int pageWidth;
  private int pageHeight;
  private int marginLeft;
  private int marginRight;

  private int marginBottom;

  private int marginTop;

  private PageConfiguration(Dimension pageSize, int topMargin, int bottomMargin, int horizontalMargin) {
    this.pageWidth = pageSize.width;
    this.pageHeight = pageSize.height;
    this.marginLeft = horizontalMargin;
    this.marginRight = horizontalMargin;
    this.marginTop = topMargin;
    this.marginBottom = bottomMargin;
  }

  private Bounds getColumnRectangle(float spaceFromTop, float height, int columnCount, float leftColumnX) {
    return new Bounds(leftColumnX, getY(spaceFromTop, height), getColumnWidth(columnCount), height);
  }

  public float getY(float spaceFromTop, float height) {
    return getUpperContentY() - spaceFromTop - height;
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

  public float getUpperContentY() {
    return pageHeight - marginTop;
  }

  public float getLeftX() {
    return marginLeft;
  }

  public float getPageHeight() {
    return pageHeight;
  }
}
