package net.sf.anathema.lib.gui.dialog.widgets;

import net.sf.anathema.lib.model.ObjectModel;
import net.sf.anathema.lib.number.Range;
import net.sf.anathema.lib.text.TextAlignment;

import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.List;

public class LineBuffer {

  private class LinePart {

    final TextBlock block;
    final int width;

    public LinePart(TextBlock block, int width) {
      this.block = block;
      this.width = width;
    }

  }

  private final List<LinePart> buffer = new ArrayList<>();
  private final FontMetrics metrics;
  private final int layoutWidth;
  private final IBlockRenderingHandler blockRenderer;
  private final TextAlignment textAlignment;
  private int blockIndexOffset = 0;
  private int lineIndex = 0;
  private final ObjectModel<TextSelection> selectionModel;

  public LineBuffer(FontMetrics metrics, int layoutWidth, IBlockRenderingHandler blockRenderer, TextAlignment textAlignment,
                    ObjectModel<TextSelection> selectionModel) {
    this.metrics = metrics;
    this.layoutWidth = layoutWidth;
    this.blockRenderer = blockRenderer;
    this.textAlignment = textAlignment;
    this.selectionModel = selectionModel;
  }

  public void add(TextBlock block, int blockWidth) {
    buffer.add(new LinePart(block, blockWidth));
  }

  public void handleNewLine() {
    renderLine(false);
  }

  public void handleAutoLineBreak() {
    if (!buffer.isEmpty()) {
      renderLine(true);
    }
  }

  private void renderLine(boolean isAutoBreak) {
    int lineWidth = calculateLineWidth();
    int spaceLeft = layoutWidth - lineWidth;
    int xOffset;
    switch (textAlignment) {
      case CENTER: {
        xOffset = spaceLeft / 2;
        break;
      }
      case RIGHT: {
        xOffset = spaceLeft;
        break;
      }
      default: {
        xOffset = 0;
        break;
      }
    }
    int x = xOffset;
    for (int blockIndex = 0; blockIndex < buffer.size(); blockIndex++) {
      TextBlock block = buffer.get(blockIndex).block;
      int blockWidth = metrics.stringWidth(block.text);
      int absoluteBlockIndex = blockIndex + blockIndexOffset;
      Range selectionRange = getSelectionRangeIfAny(selectionModel.get(), absoluteBlockIndex, block);
      blockRenderer.handleText(absoluteBlockIndex, block.text, x, lineIndex, metrics.getHeight(), selectionRange);
      x += blockWidth;
      TextBlockDelimiter delimiter = block.delimiter;
      boolean delimiterSelected = isDelimiterSelected(selectionModel.get(), block.text.length(), absoluteBlockIndex);
      if (delimiter.isNewLine()) {
        blockRenderer.handleLineEndsAt(absoluteBlockIndex, block.text.length(), x, lineIndex, metrics.getHeight());
      } else if (delimiter.isWhitespace()) {
        int delimiterWidth = delimiter.calculateWidth(metrics);
        blockRenderer
                .handleWhiteSpace(x, x + delimiterWidth, lineIndex, new TextPosition(absoluteBlockIndex, block.text.length()), metrics.getHeight(),
                        delimiterSelected);
        x += delimiterWidth;
      }
    }
    blockIndexOffset += buffer.size();
    if (isAutoBreak) {
      blockRenderer.handleLineEndsAt(blockIndexOffset - 1, buffer.get(buffer.size() - 1).block.text.length(), x, lineIndex, metrics.getHeight());
    }
    lineIndex++;
    buffer.clear();
  }

  private int calculateLineWidth() {
    int lineWidth = 0;
    int lastDelimiterWidth = 0;
    for (LinePart linePart : buffer) {
      lineWidth += linePart.width;
      lastDelimiterWidth = linePart.block.delimiter.calculateWidth(metrics);
      lineWidth += lastDelimiterWidth;
    }

    return lineWidth - lastDelimiterWidth;
  }

  private boolean isDelimiterSelected(TextSelection selection, int blockSize, int blockIndex) {
    if (selection == null) {
      return false;
    }
    if (selection.startPosition.getBlockIndex() > blockIndex) {
      return false;
    }
    if (selection.endPosition.getBlockIndex() < blockIndex) {
      return false;
    }
    if (selection.endPosition.getBlockIndex() > blockIndex) {
      return true;
    }
    int indexInBlock = selection.endPosition.getIndexInBlock();
    return indexInBlock >= blockSize;
  }

  private Range getSelectionRangeIfAny(TextSelection selection, int blockIndex, TextBlock block) {
    if (selection == null) {
      return null;
    }
    if (selection.startPosition.getBlockIndex() > blockIndex) {
      return null;
    }
    if (selection.endPosition.getBlockIndex() < blockIndex) {
      return null;
    }
    if (selection.startPosition.getBlockIndex() < blockIndex) {
      if (selection.endPosition.getBlockIndex() > blockIndex) {
        return new Range(0, block.text.length());
      }
      return new Range(0, selection.endPosition.getIndexInBlock());
    }
    if (selection.endPosition.getBlockIndex() > blockIndex) {
      return new Range(selection.startPosition.getIndexInBlock(), block.text.length());
    }
    return new Range(selection.startPosition.getIndexInBlock(), selection.endPosition.getIndexInBlock());
  }
}