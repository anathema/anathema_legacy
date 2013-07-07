package net.sf.anathema.hero.sheet.pdf.encoder.general.box;

import net.sf.anathema.hero.sheet.pdf.encoder.Bounds;
import net.sf.anathema.hero.sheet.pdf.page.PageConfiguration;

import static net.sf.anathema.hero.sheet.pdf.encoder.general.box.BoundsEncoder.ARC_SPACE;
import static net.sf.anathema.hero.sheet.pdf.encoder.general.box.BoundsEncoder.HEADER_HEIGHT;

public class BoxBoundsFactory {

  public static final int CONTENT_INSET = 5;

  public static float getContentWidth(PageConfiguration configuration, int columnSpan) {
    float boxWidth = configuration.getColumnWidth(columnSpan);
    return insetWidth(boxWidth);
  }

  public static Bounds calculateContentBounds(Bounds bounds) {
    Bounds contentBoxBounds = calculateBoxRenderBounds(bounds);
    return insetBounds(contentBoxBounds);
  }

  public static Bounds calculateBoxRenderBounds(Bounds bounds) {
    return new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - ARC_SPACE);
  }

  private static Bounds insetBounds(Bounds bounds) {
    return new Bounds(bounds.x + CONTENT_INSET, bounds.y, insetWidth(bounds.width), bounds.height - ARC_SPACE);
  }

  public static float insetWidth(float width) {
    float widthOfInsets = 2 * CONTENT_INSET;
    return width - widthOfInsets;
  }

  public static float getContentHeight(float boxHeight) {
    return boxHeight - HEADER_HEIGHT;
  }

  public static float getBoxHeight(float contentHeight) {
    return contentHeight + HEADER_HEIGHT;
  }
}
