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

public interface IBlockRenderingHandler {

  public void handleText(
      int blockIndex,
      String text,
      int x,
      int lineIndex,
      int lineHeight,
      Range optionalSelectionRange);

  public void handleWhiteSpace(
      int xMin,
      int xMax,
      int lineIndex,
      TextPosition textPosition,
      final int lineHeight,
      boolean selected);

  public void handleLineEndsAt(int blockIndex, int blockLength, int x, int lineIndex, int height);

}