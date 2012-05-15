package net.sf.anathema.lib.gui.dialog.widgets;

public class TextPosition {

  private final int indexInBlock;
  private final int blockIndex;

  public TextPosition(final int blockIndex, final int indexInBlock) {
    if (blockIndex < 0) {
      throw new IllegalArgumentException("Illegal block index " + blockIndex); //$NON-NLS-1$
    }
    if (indexInBlock < 0) {
      throw new IllegalArgumentException("Illegal index in block " + indexInBlock); //$NON-NLS-1$
    }
    this.blockIndex = blockIndex;
    this.indexInBlock = indexInBlock;
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof TextPosition)) {
      return false;
    }
    final TextPosition other = (TextPosition) obj;
    return indexInBlock == other.indexInBlock && blockIndex == other.blockIndex;
  }

  @Override
  public int hashCode() {
    return blockIndex * 5 + indexInBlock * 13;
  }

  public int getBlockIndex() {
    return blockIndex;
  }

  public int getIndexInBlock() {
    return indexInBlock;
  }
}