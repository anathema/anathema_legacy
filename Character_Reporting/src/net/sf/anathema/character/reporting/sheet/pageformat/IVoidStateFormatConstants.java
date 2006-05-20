package net.sf.anathema.character.reporting.sheet.pageformat;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;

public interface IVoidStateFormatConstants {

  public static final int HEADER_FONT_SIZE = 9;
  public static final int SUBSECTION_FONT_SIZE = 8;
  public static final int FONT_SIZE = 7;
  public static final int LINE_HEIGHT = 11;
  public static final int PADDING = 10;
  public static final int SMALL_SYMBOL_HEIGHT = FONT_SIZE;
  public static final int TEXT_PADDING = LINE_HEIGHT / 2;
  public static final int TITLE_HEIGHT = 14;
  public static final int HEALTH_RECT_SIZE = 7;
  public static final TextFormat DEFAULT_TEXT_FORMAT = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE);
  public static final int COMMENT_FONT_SIZE = FONT_SIZE - 2;
}