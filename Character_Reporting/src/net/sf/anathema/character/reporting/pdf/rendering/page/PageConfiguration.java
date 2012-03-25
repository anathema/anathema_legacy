package net.sf.anathema.character.reporting.pdf.rendering.page;

import com.itextpdf.text.Rectangle;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.framework.reporting.pdf.PageSize;

import java.awt.Dimension;

public class PageConfiguration {

  public static Offset Offset(int columnOffset) {
    return new Offset(columnOffset);
  }

  public static PageConfiguration ForPortrait(PageSize pageSize) {
    Rectangle rectangle = pageSize.getPortraitRectangle();
    Dimension size = new Dimension((int) rectangle.getWidth(), (int) rectangle.getHeight());
    int overallColumnCount = 3;
    int columnSpacing = 10;
    return new PageConfiguration(size, Margins.ForPortraitSheet(), overallColumnCount, columnSpacing);
  }

  public static PageConfiguration ForLandscape(PageSize pageSize) {
    Rectangle rectangle = pageSize.getPortraitRectangle();
    Dimension size = new Dimension((int) rectangle.getHeight(), (int) rectangle.getWidth());
    int overallColumnCount = 5;
    int columnSpacing = 8;
    return new PageConfiguration(size, Margins.ForLandscapeSheet(), overallColumnCount, columnSpacing);
  }

  private Dimension size;
  private Margins margins;
  private int overallColumnCount = 3;
  private float columnSpacing;

  private PageConfiguration(Dimension size, Margins margins, int overallColumnCount, float columnSpacing) {
    this.size = size;
    this.margins = margins;
    this.overallColumnCount = overallColumnCount;
    this.columnSpacing = columnSpacing;
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
    int spacingCount = overallColumnCount - 1;
    float oneColumnWidth = (getContentWidth() - spacingCount * columnSpacing) / overallColumnCount;
    return oneColumnWidth * columnCount + columnSpacing * (columnCount - 1);
  }

  public float getContentHeight() {
    return size.height - margins.bottom - margins.top;
  }

  public float getContentWidth() {
    return size.width - margins.left - margins.right;
  }

  public float getLowerContentY() {
    return margins.bottom;
  }

  public float getLeftColumnX(int columnIndex) {
    return columnIndex * (getColumnWidth() + columnSpacing) + margins.left;
  }

  public Bounds getColumnRectangle(float spaceFromTop, float height, int columnCount, Offset offset) {
    return getColumnRectangle(spaceFromTop, height, columnCount, getLeftColumnX(offset.value()));
  }

  public float getUpperContentY() {
    return size.height - margins.top;
  }

  public float getLeftX() {
    return margins.left;
  }

  public float getPageHeight() {
    return size.height;
  }
}
