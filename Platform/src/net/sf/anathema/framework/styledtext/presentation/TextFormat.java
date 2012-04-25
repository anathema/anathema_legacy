package net.sf.anathema.framework.styledtext.presentation;

import java.awt.Color;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.framework.styledtext.model.ITextFormat;

public class TextFormat implements ITextFormat {

  private final FontStyle fontStyle;
  private final boolean underline;
  private final Integer fontSize;
  private final String fontName;
  private final Color textColor;

  public TextFormat() {
    this(FontStyle.PLAIN, false, null);
  }

  public TextFormat(String fontName) {
    this(fontName, FontStyle.PLAIN, false, null);
  }

  public TextFormat(Color textColor) {
    this(null, FontStyle.PLAIN, false, null, textColor);
  }

  public TextFormat(FontStyle fontStyle, boolean underline) {
    this(fontStyle, underline, null);
  }

  public TextFormat(FontStyle fontStyle, boolean underline, Integer fontSize) {
    this(null, fontStyle, underline, fontSize);
  }

  public TextFormat(String fontName, FontStyle fontStyle, boolean underline, Integer fontSize) {
    this(fontName, fontStyle, underline, fontSize, null);
  }

  public TextFormat(String fontName, FontStyle fontStyle, boolean underline, Integer fontSize, Color textColor) {
    this.fontStyle = fontStyle;
    this.underline = underline;
    this.fontSize = fontSize;
    this.fontName = fontName;
    this.textColor = textColor;
  }

  @Override
  public boolean isStyled() {
    return isUnderline() || !fontStyle.isPlain() || getFontSize() != null || fontName != null || textColor != null;
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
    return "[" + fontStyle + "," + isUnderline() + "," + getFontSize() + "," + getColor() + "," + getFontName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
  }

  @Override
  public Integer getFontSize() {
    return fontSize;
  }

  @Override
  public String getFontName() {
    return fontName;
  }

  @Override
  public Color getColor() {
    return textColor;
  }
}