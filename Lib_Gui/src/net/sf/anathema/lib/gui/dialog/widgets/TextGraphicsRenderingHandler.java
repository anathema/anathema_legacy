/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.widgets;

import com.google.common.base.Preconditions;
import net.disy.commons.core.util.Range;
import net.sf.anathema.lib.gui.swing.SwingColors;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

public final class TextGraphicsRenderingHandler implements IBlockRenderingHandler {

  private final Graphics graphics;
  private final FontMetrics metrics;
  private final Color foregroundColor;

  public TextGraphicsRenderingHandler(final Graphics graphics, final Color foregroundColor) {
    Preconditions.checkNotNull(graphics);
    Preconditions.checkNotNull(foregroundColor);
    this.graphics = graphics;
    this.foregroundColor = foregroundColor;
    metrics = graphics.getFontMetrics();
  }

  @Override
  public void handleText(
      final int blockIndex,
      final String text,
      final int x,
      final int lineIndex,
      final int lineHeight,
      final Range optionalSelectionRange) {
    final int yOffset = metrics.getLeading() + metrics.getAscent();
    final int y = yOffset + lineIndex * lineHeight;
    if (optionalSelectionRange != null) {
      final String prefix = text.substring(
          0,
          Math.min(text.length(), optionalSelectionRange.getLowerBound()));
      final int dStartX = metrics.stringWidth(prefix);
      final String selectionPart = text.substring(
          optionalSelectionRange.getLowerBound(),
          Math.min(text.length(), optionalSelectionRange.getUpperBound()));
      final String endPart = text.substring(Math.min(
          text.length(),
          optionalSelectionRange.getUpperBound()));
      final int dEndX = metrics.stringWidth(selectionPart);
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
      final int xMin,
      final int xMax,
      final int lineIndex,
      final TextPosition textPosition,
      final int lineHeight,
      final boolean selected) {
    if (selected) {
      graphics.setColor(SwingColors.getTextAreaSelectionBackgroundColor());
      graphics.fillRect(xMin, lineIndex * lineHeight, xMax - xMin, lineHeight);
    }
  }

  @Override
  public void handleLineEndsAt(
      final int blockIndex,
      final int blockLength,
      final int x,
      final int lineIndex,
      final int height) {
    //nothing to do
  }
}