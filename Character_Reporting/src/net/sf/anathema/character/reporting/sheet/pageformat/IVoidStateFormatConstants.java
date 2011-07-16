package net.sf.anathema.character.reporting.sheet.pageformat;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;

public interface IVoidStateFormatConstants {

  public static final int HEADER_FONT_SIZE = 9;
  public static final int SUBSECTION_FONT_SIZE = 8;
  public static final int FONT_SIZE = 7;
  public static final int COMMENT_FONT_SIZE = FONT_SIZE - 2;
  
  public static final float LINE_HEIGHT = 11f;
  public static final float BARE_LINE_HEIGHT = 10.5f;
  public static final float PADDING = 10f;
  public static final float SMALL_SYMBOL_HEIGHT = FONT_SIZE;
  public static final float TEXT_PADDING = LINE_HEIGHT / 2f;
  public static final float TITLE_HEIGHT = 14f;
  public static final float HEALTH_RECT_SIZE = 7f;
  public static final TextFormat DEFAULT_TEXT_FORMAT = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE);
}