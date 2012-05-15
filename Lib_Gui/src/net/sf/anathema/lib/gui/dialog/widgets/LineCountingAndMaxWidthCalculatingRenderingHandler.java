/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
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
  public void handleText(
      final int blockIndex,
      final String text,
      final int x,
      final int lineIndex,
      final int lineHeight,
      final Range optionalSelectionRange) {
    super.handleText(blockIndex, text, x, lineIndex, lineHeight, optionalSelectionRange);
    maxLineWidth = Math.max(maxLineWidth, x + fontMetrics.stringWidth(text));
  }

  public int getMaxLineWidth() {
    return maxLineWidth;
  }
}