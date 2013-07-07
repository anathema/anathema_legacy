package net.sf.anathema.hero.concept.sheet.personal.encoder;

import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TEXT_PADDING;

public class Grid {

  private Bounds bounds;
  private int rowCount;
  private int columnCount;

  public Grid(Bounds bounds, int rowCount, int columnCount) {
    this.bounds = bounds;
    this.rowCount = rowCount;
    this.columnCount = columnCount;
  }

  public Position getPosition(int columnIndex, int rowIndex) {
    return new Position(getColumnX(columnIndex), getRowY(rowIndex));
  }

  public float getWidth(int columnCount) {
    return columnCount * getEntryWidth() + (columnCount - 1) * TEXT_PADDING;
  }

  private float getRowY(int rowIndex) {
    return bounds.getMaxY() - (rowIndex + 1) * getRowHeight();
  }

  private float getColumnX(int columnIndex) {
    return bounds.x + columnIndex * (getEntryWidth() + TEXT_PADDING);
  }

  private float getRowHeight() {
    return (bounds.height - TEXT_PADDING) / rowCount;
  }

  private float getEntryWidth() {
    float padding = (columnCount - 1) * TEXT_PADDING;
    return (bounds.width - padding) / columnCount;
  }
}
