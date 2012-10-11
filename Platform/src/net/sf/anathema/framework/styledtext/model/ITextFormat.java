package net.sf.anathema.framework.styledtext.model;

import net.sf.anathema.lib.text.FontStyle;

public interface ITextFormat {

  boolean isUnderline();

  FontStyle getFontStyle();

  boolean isStyled();
}