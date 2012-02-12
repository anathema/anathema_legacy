package net.sf.anathema.character.reporting.pdf.rendering.page;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;

public interface IVoidStateFormatConstants {

  int HEADER_FONT_SIZE = 9;
  int SUBSECTION_FONT_SIZE = 8;
  int FONT_SIZE = 7;
  float TABLE_FONT_SIZE = FONT_SIZE - 0.5f;
  int SMALLER_FONT_SIZE = 6;
  int COMMENT_FONT_SIZE = 5;

  float LINE_HEIGHT = 11f;
  float REDUCED_LINE_HEIGHT = LINE_HEIGHT - 2;
  float BARE_LINE_HEIGHT = 10.5f;
  float PADDING = 10f;
  float SMALL_SYMBOL_HEIGHT = FONT_SIZE;
  float TEXT_PADDING = LINE_HEIGHT / 2f;
  float TITLE_HEIGHT = 14f;
  float HEALTH_RECT_SIZE = 7f;
  TextFormat DEFAULT_TEXT_FORMAT = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE);
  float WEAPON_HEIGHT_FIRST_EDITION = 129;
  float WEAPON_HEIGHT_SECOND_EDITION = 102;
  float TYPE_LONG_FORM_CUTOFF = 20;
}
