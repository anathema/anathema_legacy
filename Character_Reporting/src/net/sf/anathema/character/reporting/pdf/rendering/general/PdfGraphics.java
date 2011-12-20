package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.COMMENT_FONT_SIZE;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.FONT_SIZE;

public class PdfGraphics implements ITextMetrics {
  private final PdfContentByte directContent;
  private BaseFont baseFont;

  public PdfGraphics(PdfContentByte directContent, BaseFont baseFont) {
    this.directContent = directContent;
    this.baseFont = baseFont;
  }

  @Deprecated
  public PdfContentByte getDirectContent() {
    return directContent;
  }

  @Deprecated
  public BaseFont getBaseFont() {
    return baseFont;
  }

  @Override
  public final float getDefaultTextWidth(String text) {
    return baseFont.getWidthPoint(text, FONT_SIZE);
  }

  @Override
  public final float getCommentTextWidth(String text) {
    return baseFont.getWidthPoint(text, COMMENT_FONT_SIZE);
  }

  public final void drawMissingTextLine(Position position, float length) {
    setFillColorBlack(directContent);
    directContent.setLineWidth(0);
    directContent.moveTo(position.x, position.y);
    directContent.lineTo(position.x + length, position.y);
    directContent.stroke();
  }

  public final void drawComment(String text, Position position, int alignment) {
    setFillColorBlack(directContent);
    setCommentFont(directContent);
    directContent.setLineWidth(0);
    drawText(text, position, alignment, 0);
  }

  public final void drawText(String text, Position position, int alignment) {
    addText(text, position, alignment, 0);
  }

  public final void drawVerticalText(String text, Position position, int alignment) {
    addText(text, position, alignment, 90);
  }

  public final void drawLabelledContent(String label, String content, Position position, float width) {
    initDirectContentForText();
    directContent.beginText();
    directContent.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, label, position.x, position.y, 0);
    float labelWidth = getDefaultTextWidth(label);
    float contentX = position.x + labelWidth + 2;
    if (StringUtilities.isNullOrTrimEmpty(content)) {
      directContent.endText();
      float lineWidth = position.x + width - contentX;
      drawMissingTextLine(new Position(contentX, position.y), lineWidth);
    }
    else {
      directContent.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, content, contentX, position.y, 0);
      directContent.endText();
    }
  }

  private void addText(String text, Position position, int alignment, int rotation) {
    initDirectContentForText();
    drawText(text, position, alignment, rotation);
  }

  private void drawText(String text, Position position, int alignment, int rotation) {
    directContent.beginText();
    directContent.showTextAlignedKerned(alignment, text, position.x, position.y, rotation);
    directContent.endText();
  }

  private void initDirectContentForText() {
    setFillColorBlack(directContent);
    setDefaultFont(directContent);
    directContent.setLineWidth(0);
  }

  private final void setFillColorBlack(PdfContentByte directContent) {
     directContent.setRGBColorFill(0, 0, 0);
   }

  private final void setCommentFont(PdfContentByte directContent) {
    directContent.setFontAndSize(baseFont, COMMENT_FONT_SIZE);
  }

  private final void setDefaultFont(PdfContentByte directContent) {
    directContent.setFontAndSize(baseFont, FONT_SIZE);
  }
}
