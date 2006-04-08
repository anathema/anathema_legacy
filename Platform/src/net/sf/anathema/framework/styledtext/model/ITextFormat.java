package net.sf.anathema.framework.styledtext.model;

import java.awt.Color;

import net.disy.commons.core.text.font.FontStyle;

public interface ITextFormat {

  public boolean isUnderline();

  public FontStyle getFontStyle();

  public Integer getFontSize();

  public String getFontName();

  public Color getColor();

  public boolean isStyled();
}