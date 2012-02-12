package net.sf.anathema.character.reporting.pdf.layout.field;

import net.sf.anathema.character.reporting.pdf.layout.Body;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.GraphicsTemplate;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class LayoutField {

  public static LayoutField CreateUpperLeftFieldWithHeightAndColumnSpan(Body body, float height, int columnSpan) {
    return new LayoutField(body, columnSpan, 0, height, 0);
  }

  private final Body body;
  private final int columnSpan;
  private final int columnIndex;
  private final float height;
  private final float fromTop;

  public LayoutField(Body body, int columnSpan, int columnIndex, float height, float fromTop) {
    this.body = body;
    this.columnSpan = columnSpan;
    this.columnIndex = columnIndex;
    this.height = height;
    this.fromTop = fromTop;
  }

  public Bounds createRenderBounds() {
    return new Bounds(0, 0, getWidth(), height);
  }

  public GraphicsTemplate createRenderTemplate(SheetGraphics graphics) {
    return graphics.createTemplate(getWidth(), height);
  }

  public void addTemplateToParent(GraphicsTemplate template) {
    template.addToParentAt(getX(), getY());
  }

  private float getX() {
    return body.configuration.getLeftColumnX(columnIndex);
  }

  private float getY() {
    return body.configuration.getY(fromTop, height);
  }

  private float getWidth() {
    return body.configuration.getColumnWidth(columnSpan);
  }

  public float getFromTopBelow() {
    return getBottomFromTop() + PADDING;
  }

  public float getBottomFromTop() {
    return fromTop + height;
  }

  public float getAlignedHeight() {
    return height;
  }

  public float getAlignedFromTop() {
    return fromTop;
  }

  public float getRemainingColumnHeight() {
    return body.contentHeight - getFromTopBelow();
  }

  public float getHeightToBottomFrom(LayoutField parent) {
    return parent.height - height - PADDING;
  }

  public int getColumnIndexBelow() {
    return columnIndex;
  }

  public int getColumnIndexOnRight() {
    return columnIndex + columnSpan;
  }

  public LayoutField createForFromTopAndHeightAndColumnSpanAndColumnIndex(float newFromTop, HeightStrategy heightStrategy, int newColumnSpan, int newColumnIndex) {
    float newWidth = body.configuration.getColumnWidth(newColumnSpan);
    float newHeight = heightStrategy.getHeight(newWidth);
    return new LayoutField(body, newColumnSpan, newColumnIndex, newHeight, newFromTop);
  }
}
