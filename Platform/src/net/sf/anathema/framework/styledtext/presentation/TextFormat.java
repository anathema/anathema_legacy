package net.sf.anathema.framework.styledtext.presentation;

import net.sf.anathema.framework.styledtext.model.ITextFormat;
import net.sf.anathema.lib.text.FontStyle;

public class TextFormat implements ITextFormat {

  private final FontStyle fontStyle;
  private final boolean underline;

  public TextFormat() {
    this(FontStyle.PLAIN, false);
  }

  public TextFormat(FontStyle fontStyle, boolean underline) {
    this.fontStyle = fontStyle;
    this.underline = underline;
  }

  @Override
  public boolean isStyled() {
    return isUnderline() || !fontStyle.isPlain();
  }

  @Override
  public boolean isUnderline() {
    return underline;
  }

  @Override
  public FontStyle getFontStyle() {
    return fontStyle;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TextFormat)) {
      return false;
    }
    TextFormat otherFormat = (TextFormat) obj;
    return otherFormat.fontStyle.equals(fontStyle) && otherFormat.underline == underline;
  }

  @Override
  public int hashCode() {
    return fontStyle.hashCode();
  }

  @Override
  public String toString() {
    return "[" + fontStyle + "," + isUnderline() + "]";
  }
}