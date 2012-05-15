package net.sf.anathema.lib.gui.dialog.widgets;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.swing.SwingColors;
import net.sf.anathema.lib.util.Range;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

public final class TextGraphicsRenderingHandler implements IBlockRenderingHandler {

  private final Graphics graphics;
  private final FontMetrics metrics;
  private final Color foregroundColor;

  public TextGraphicsRenderingHandler(Graphics graphics, Color foregroundColor) {
    Preconditions.checkNotNull(graphics);
    Preconditions.checkNotNull(foregroundColor);
    this.graphics = graphics;
    this.foregroundColor = foregroundColor;
    metrics = graphics.getFontMetrics();
  }

  @Override
  public void handleText(
      int blockIndex,
      String text,
      int x,
      int lineIndex,
      int lineHeight,
      Range optionalSelectionRange) {
    int yOffset = metrics.getLeading() + metrics.getAscent();
    int y = yOffset + lineIndex * lineHeight;
    if (optionalSelectionRange != null) {
      String prefix = text.substring(
          0,
          Math.min(text.length(), optionalSelectionRange.getLowerBound()));
      int dStartX = metrics.stringWidth(prefix);
      String selectionPart = text.substring(
          optionalSelectionRange.getLowerBound(),
          Math.min(text.length(), optionalSelectionRange.getUpperBound()));
      String endPart = text.substring(Math.min(
          text.length(),
          optionalSelectionRange.getUpperBound()));
      int dEndX = metrics.stringWidth(selectionPart);
      graphics.setColor(SwingColors.getTextAreaSelectionBackgroundColor());
      graphics.fillRect(x + dStartX, lineIndex * lineHeight, dEndX, lineHeight);

      graphics.setColor(foregroundColor);
      if (prefix.length() > 0) {
        graphics.drawString(prefix, x, y);
      }
      if (endPart.length() > 0) {
        graphics.drawString(endPart, x + dStartX + dEndX, y);
      }
      graphics.setColor(SwingColors.getTextAreaSelectionForegroundColor());
      graphics.drawString(selectionPart, x + dStartX, y);
    }
    else {
      graphics.setColor(foregroundColor);
      graphics.drawString(text, x, y);
    }
  }

  @Override
  public void handleWhiteSpace(
      int xMin,
      int xMax,
      int lineIndex,
      TextPosition textPosition,
      int lineHeight,
      boolean selected) {
    if (selected) {
      graphics.setColor(SwingColors.getTextAreaSelectionBackgroundColor());
      graphics.fillRect(xMin, lineIndex * lineHeight, xMax - xMin, lineHeight);
    }
  }

  @Override
  public void handleLineEndsAt(
      int blockIndex,
      int blockLength,
      int x,
      int lineIndex,
      int height) {
    //nothing to do
  }
}