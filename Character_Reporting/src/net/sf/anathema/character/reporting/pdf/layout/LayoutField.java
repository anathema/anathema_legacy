package net.sf.anathema.character.reporting.pdf.layout;

import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.GraphicsTemplate;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class LayoutField {

  public static LayoutField CreateUpperLeftFieldWithHeightAndColumnSpan(PageConfiguration configuration, float height, int  columnSpan, float contentHeight) {
    return new LayoutField(configuration, columnSpan, 0, height, 0, contentHeight);
  }

  private final PageConfiguration configuration;
  private float contentHeight;
  private final int columnSpan;
  private final int columnIndex;
  private final float height;
  private final float fromTop;

  public LayoutField(PageConfiguration configuration, int columnSpan, int columnIndex, float height, float fromTop, float contentHeight) {
    this.configuration = configuration;
    this.columnSpan = columnSpan;
    this.columnIndex = columnIndex;
    this.height = height;
    this.fromTop = fromTop;
    this.contentHeight = contentHeight;
  }

  public LayoutField placeBelowWithHeight(float height) {
    return new LayoutField(configuration, 1, columnIndex, height, getFromTopBelow(), contentHeight);
  }

  public LayoutField placeDoubleColumnBelowWithHeight(float height) {
    return new LayoutField(configuration, 2, columnIndex, height, getFromTopBelow(), contentHeight);
  }

 public LayoutField placeBelowBottomAlignedTo(LayoutField field) {
    float dynamicHeight = field.height - height - PADDING;
    return new LayoutField(configuration, 1, columnIndex, dynamicHeight, getFromTopBelow(), contentHeight);
   }

  public LayoutField fillRemainingColumnBelow() {
    float remainingHeight = contentHeight - getFromTopBelow();
    return new LayoutField(configuration, 1, columnIndex, remainingHeight, getFromTopBelow(), contentHeight);
  }

  public LayoutField fillRemainingDoubleColumnBelow() {
    float remainingHeight = contentHeight - getFromTopBelow();
    return new LayoutField(configuration, 2, columnIndex, remainingHeight, getFromTopBelow(), contentHeight);
  }

  public LayoutField placeOnRightSide() {
    return new LayoutField(configuration, 1, columnIndex + columnSpan, height, fromTop, contentHeight);
  }

  public LayoutField placeOnRightSideWithHeight(float height) {
    return new LayoutField(configuration, 1, columnIndex + columnSpan, height, fromTop, contentHeight);
  }

  public Bounds createRenderBounds() {
    return new Bounds(0, 0, getWidth(), getHeight());
  }

  public GraphicsTemplate createRenderTemplate(SheetGraphics graphics) {
    return graphics.createTemplate(getWidth(), getHeight());
  }

  public void addTemplateToParent(GraphicsTemplate template) {
    template.addToParentAt(getX(), getY());
  }

  private float getFromTopBelow() {
    return fromTop + height + PADDING;
  }

  private float getX() {
    return configuration.getLeftColumnX(columnIndex);
  }

  private float getY() {
    return configuration.getY(fromTop, height);
  }

  public float getHeight() {
    return height;
  }

  private float getWidth() {
    return configuration.getColumnWidth(columnSpan);
  }
}
