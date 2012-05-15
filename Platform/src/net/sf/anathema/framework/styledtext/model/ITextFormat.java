package net.sf.anathema.framework.styledtext.model;

import net.sf.anathema.lib.text.FontStyle;

import java.awt.Color;

public interface ITextFormat {

  boolean isUnderline();

  FontStyle getFontStyle();

  Integer getFontSize();

  String getFontName();

  Color getColor();

  boolean isStyled();
}