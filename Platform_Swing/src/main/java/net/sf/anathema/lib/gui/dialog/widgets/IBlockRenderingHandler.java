package net.sf.anathema.lib.gui.dialog.widgets;

import net.sf.anathema.lib.number.Range;

public interface IBlockRenderingHandler {

  void handleText(int blockIndex, String text, int x, int lineIndex, int lineHeight, Range optionalSelectionRange);

  void handleWhiteSpace(int xMin, int xMax, int lineIndex, TextPosition textPosition, int lineHeight, boolean selected);

  void handleLineEndsAt(int blockIndex, int blockLength, int x, int lineIndex, int height);
}