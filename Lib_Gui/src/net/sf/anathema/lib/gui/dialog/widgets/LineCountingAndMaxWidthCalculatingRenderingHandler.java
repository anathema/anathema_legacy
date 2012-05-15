package net.sf.anathema.lib.gui.dialog.widgets;

import net.sf.anathema.lib.util.Range;

import java.awt.FontMetrics;

public final class LineCountingAndMaxWidthCalculatingRenderingHandler extends LineCountingRenderingHandler {
  private final FontMetrics fontMetrics;

  private int maxLineWidth = 0;

  public LineCountingAndMaxWidthCalculatingRenderingHandler(FontMetrics fontMetrics) {
    super();
    this.fontMetrics = fontMetrics;
  }

  @Override
  public void handleText(int blockIndex, String text, int x, int lineIndex, int lineHeight, Range optionalSelectionRange) {
    super.handleText(blockIndex, text, x, lineIndex, lineHeight, optionalSelectionRange);
    maxLineWidth = Math.max(maxLineWidth, x + fontMetrics.stringWidth(text));
  }

  public int getMaxLineWidth() {
    return maxLineWidth;
  }
}