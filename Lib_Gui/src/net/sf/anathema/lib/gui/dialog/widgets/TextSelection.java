package net.sf.anathema.lib.gui.dialog.widgets;

import com.google.common.base.Preconditions;

public class TextSelection {

  public static TextSelection createSelection(
      final TextPosition position1,
      final TextPosition position2) {
    Preconditions.checkNotNull(position1);
    Preconditions.checkNotNull(position2);
    if (position1.getBlockIndex() < position2.getBlockIndex()) {
      return new TextSelection(position1, position2);
    }
    if (position1.getBlockIndex() == position2.getBlockIndex()
        && position1.getIndexInBlock() <= position2.getIndexInBlock()) {
      return new TextSelection(position1, position2);
    }
    return new TextSelection(position2, position1);
  }

  public final TextPosition endPosition;
  public final TextPosition startPosition;

  private TextSelection(final TextPosition startPosition, final TextPosition endPosition) {
    this.startPosition = startPosition;
    this.endPosition = endPosition;
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof TextSelection)) {
      return false;
    }
    final TextSelection other = (TextSelection) obj;
    return startPosition.equals(other.startPosition) && endPosition.equals(other.endPosition);
  }

  @Override
  public int hashCode() {
    return startPosition.hashCode() + endPosition.hashCode() * 17;
  }
}