package net.sf.anathema.development.reporting.encoder.voidstate.format;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;

public interface IVoidStateFormatConstants {

  public static final int HEADER_FONT_SIZE = 9;
  public static final int FONT_SIZE = 7;
  public static final int LINE_HEIGHT = 11;
  public static final int PADDING = 10;
  public static final int SMALL_SYMBOL_HEIGHT = FONT_SIZE;
  public static final int TEXT_PADDING = LINE_HEIGHT / 2;
  public static final int TITLE_HEIGHT = 14;
  public static final int HEALTH_RECT_SIZE = 7;
  public static final TextFormat DEFAULT_TEXT_FORMAT = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE);
}