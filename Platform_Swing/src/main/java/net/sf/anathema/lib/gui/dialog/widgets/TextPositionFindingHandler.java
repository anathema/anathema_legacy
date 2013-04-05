package net.sf.anathema.lib.gui.dialog.widgets;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.number.Range;

import java.awt.FontMetrics;
import java.awt.Point;

public final class TextPositionFindingHandler implements IBlockRenderingHandler {
  private final FontMetrics metrics;
  private final Point point;
  private TextPosition textPosition;

  public TextPositionFindingHandler(FontMetrics metrics, Point point) {
    Preconditions.checkNotNull(metrics);
    Preconditions.checkNotNull(point);
    this.metrics = metrics;
    this.point = point;
  }

  @Override
  public void handleText(
      int blockIndex,
      String text,
      int x,
      int lineIndex,
      int lineHeight,
      Range optionalSelectionRange) {
    int stringWidth = metrics.stringWidth(text);
    if (isVerticallyInside(lineIndex, lineHeight)) {
      if (isHorizontallyInside(x, (x + stringWidth))) {
        int indexInBlock = getIndexInBlock(metrics, text, point.x - x);
        this.textPosition = new TextPosition(blockIndex, indexInBlock);
      }
      else if (this.textPosition == null) {
        this.textPosition = new TextPosition(blockIndex, 0);
      }
    }
  }

  private static int getIndexInBlock(FontMetrics metrics, String text, int x) {
    int index = 1;
    while (index <= text.length()) {
      String substring = text.substring(0, index);
      int width = metrics.stringWidth(substring);
      if (width > x) {
        return index - 1;
      }
      ++index;
    }
    return text.length();
  }

  private boolean isHorizontallyInside(int minX, int maxX) {
    return point.x >= minX && point.x <= maxX;
  }

  private boolean isVerticallyInside(int lineIndex, int lineHeight) {
    int yOffset = metrics.getLeading() + metrics.getAscent();
    int yMin = yOffset + (lineIndex - 1) * lineHeight;
    int yMax = yMin + lineHeight;
    return point.y <= yMax && point.y >= yMin;
  }

  @Override
  public void handleWhiteSpace(
      int minX,
      int maxX,
      int lineIndex,
      TextPosition whiteSpacePosition,
      int lineHeight,
      boolean selected) {
    if (isVerticallyInside(lineIndex, lineHeight) && isHorizontallyInside(minX, maxX)) {
      this.textPosition = whiteSpacePosition;
    }
  }

  @Override
  public void handleLineEndsAt(
      int blockIndex,
      int blockLength,
      int x,
      int lineIndex,
      int height) {
    if (isVerticallyInside(lineIndex, height) && point.x >= x) {
      this.textPosition = new TextPosition(blockIndex, blockLength);
    }
  }

  public TextPosition getTextPosition() {
    return textPosition;
  }
}