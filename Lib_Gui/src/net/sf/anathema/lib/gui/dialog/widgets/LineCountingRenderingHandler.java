/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.widgets;

import net.disy.commons.core.number.MaxIntegerValueBuilder;
import net.sf.anathema.lib.util.Range;

public class LineCountingRenderingHandler implements IBlockRenderingHandler {
  private final MaxIntegerValueBuilder maxLineNumberBuilder = new MaxIntegerValueBuilder(0);

  @Override
  public void handleText(
      final int blockIndex,
      final String text,
      final int x,
      final int lineIndex,
      final int lineHeight,
      final Range optionalSelectionRange) {
    maxLineNumberBuilder.add(lineIndex + 1);
  }

  @Override
  public void handleWhiteSpace(
      final int min,
      final int max,
      final int lineIndex,
      final TextPosition textPosition,
      final int lineHeight,
      final boolean selected) {
    maxLineNumberBuilder.add(lineIndex + 1);
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

  public int getLineCount() {
    return maxLineNumberBuilder.getMaximum();
  }
}