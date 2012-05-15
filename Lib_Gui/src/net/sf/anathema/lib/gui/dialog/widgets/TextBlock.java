package net.sf.anathema.lib.gui.dialog.widgets;

import com.google.common.base.Preconditions;

public class TextBlock {

  public final String text;
  public final TextBlockDelimiter delimiter;

  public TextBlock(String text, TextBlockDelimiter delimiter) {
    Preconditions.checkNotNull(text);
    Preconditions.checkNotNull(delimiter);
    this.text = text;
    this.delimiter = delimiter;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TextBlock)) {
      return false;
    }
    TextBlock other = (TextBlock) obj;
    return delimiter == other.delimiter && text.equals(other.text);
  }

  @Override
  public int hashCode() {
    return delimiter.hashCode() * 17 + text.hashCode() * 3;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{" + "text='" + text + "', delimiter=" + delimiter + "}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  }
}