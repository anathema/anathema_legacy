package net.sf.anathema.character.reporting.pdf.rendering.graphics;

import com.itextpdf.text.pdf.BaseFont;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.*;

public class SheetTextMetrics implements TextMetrics {

  private BaseFont baseFont;

  public SheetTextMetrics(BaseFont baseFont) {
    this.baseFont = baseFont;
  }

  @Override
  public final float getDefaultTextWidth(String text) {
    return baseFont.getWidthPoint(text, FONT_SIZE);
  }

  @Override
  public final float getCommentTextWidth(String text) {
    return baseFont.getWidthPoint(text, COMMENT_FONT_SIZE);
  }

  @Override
  public float getTableTextWidth(String text) {
    return baseFont.getWidthPoint(text, TABLE_FONT_SIZE);
  }
}
