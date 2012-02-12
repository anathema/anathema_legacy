package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;

public class BoxBoundsFactory {

  public static final int CONTENT_INSET = 5;

  public static float getContentWidth(PageConfiguration configuration, int columnSpan) {
    float boxWidth = configuration.getColumnWidth(columnSpan);
    return insetWidth(boxWidth);
  }

  public static Bounds calculateContentBounds(Bounds bounds) {
    Bounds contentBoxBounds = calculateBoxBounds(bounds);
    return insetBounds(contentBoxBounds);
  }

  public static Bounds calculateBoxBounds(Bounds bounds) {
    float headerPadding = BoundsEncoder.HEADER_HEIGHT / 2;
    return new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - headerPadding);
  }

  private static Bounds insetBounds(Bounds bounds) {
    return new Bounds(bounds.x + CONTENT_INSET, bounds.y, insetWidth(bounds.width), bounds.height - BoundsEncoder.ARC_SPACE);
  }

  public static float insetWidth(float width) {
    float widthOfInsets = 2 * CONTENT_INSET;
    return width - widthOfInsets;
  }
}
