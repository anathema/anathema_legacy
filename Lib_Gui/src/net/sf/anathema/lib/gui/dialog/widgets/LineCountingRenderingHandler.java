package net.sf.anathema.lib.gui.dialog.widgets;

import net.sf.anathema.lib.number.MaxIntegerValueBuilder;
import net.sf.anathema.lib.util.Range;

public class LineCountingRenderingHandler implements IBlockRenderingHandler {
  private final MaxIntegerValueBuilder maxLineNumberBuilder = new MaxIntegerValueBuilder(0);

  @Override
  public void handleText(int blockIndex, String text, int x, int lineIndex, int lineHeight, Range optionalSelectionRange) {
    maxLineNumberBuilder.add(lineIndex + 1);
  }

  @Override
  public void handleWhiteSpace(int min, int max, int lineIndex, TextPosition textPosition, int lineHeight, boolean selected) {
    maxLineNumberBuilder.add(lineIndex + 1);
  }

  @Override
  public void handleLineEndsAt(int blockIndex, int blockLength, int x, int lineIndex, int height) {
    //nothing to do
  }

  public int getLineCount() {
    return maxLineNumberBuilder.getMaximum();
  }
}