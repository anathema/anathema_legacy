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
import net.sf.anathema.lib.util.Range;

import java.awt.FontMetrics;
import java.awt.Point;

public final class TextPositionFindingHandler implements IBlockRenderingHandler {
  private final FontMetrics metrics;
  private final Point point;
  private TextPosition textPosition;

  public TextPositionFindingHandler(final FontMetrics metrics, final Point point) {
    Preconditions.checkNotNull(metrics);
    Preconditions.checkNotNull(point);
    this.metrics = metrics;
    this.point = point;
  }

  @Override
  public void handleText(
      final int blockIndex,
      final String text,
      final int x,
      final int lineIndex,
      final int lineHeight,
      final Range optionalSelectionRange) {
    final int stringWidth = metrics.stringWidth(text);
    if (isVerticallyInside(lineIndex, lineHeight)) {
      if (isHorizontallyInside(x, (x + stringWidth))) {
        final int indexInBlock = getIndexInBlock(metrics, text, point.x - x);
        this.textPosition = new TextPosition(blockIndex, indexInBlock);
      }
      else if (this.textPosition == null) {
        this.textPosition = new TextPosition(blockIndex, 0);
      }
    }
  }

  private static int getIndexInBlock(final FontMetrics metrics, final String text, final int x) {
    int index = 1;
    while (index <= text.length()) {
      final String substring = text.substring(0, index);
      final int width = metrics.stringWidth(substring);
      if (width > x) {
        return index - 1;
      }
      ++index;
    }
    return text.length();
  }

  private boolean isHorizontallyInside(final int minX, final int maxX) {
    return point.x >= minX && point.x <= maxX;
  }

  private boolean isVerticallyInside(final int lineIndex, final int lineHeight) {
    final int yOffset = metrics.getLeading() + metrics.getAscent();
    final int yMin = yOffset + (lineIndex - 1) * lineHeight;
    final int yMax = yMin + lineHeight;
    return point.y <= yMax && point.y >= yMin;
  }

  @Override
  public void handleWhiteSpace(
      final int minX,
      final int maxX,
      final int lineIndex,
      final TextPosition whiteSpacePosition,
      final int lineHeight,
      final boolean selected) {
    if (isVerticallyInside(lineIndex, lineHeight) && isHorizontallyInside(minX, maxX)) {
      this.textPosition = whiteSpacePosition;
    }
  }

  @Override
  public void handleLineEndsAt(
      final int blockIndex,
      final int blockLength,
      final int x,
      final int lineIndex,
      final int height) {
    if (isVerticallyInside(lineIndex, height) && point.x >= x) {
      this.textPosition = new TextPosition(blockIndex, blockLength);
    }
  }

  public TextPosition getTextPosition() {
    return textPosition;
  }
}