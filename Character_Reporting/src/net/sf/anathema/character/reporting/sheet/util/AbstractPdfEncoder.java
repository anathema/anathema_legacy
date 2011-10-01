package net.sf.anathema.character.reporting.sheet.util;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public abstract class AbstractPdfEncoder {

  protected abstract BaseFont getBaseFont();

  protected final void setCommentFont(PdfContentByte directContent) {
    directContent.setFontAndSize(getBaseFont(), IVoidStateFormatConstants.COMMENT_FONT_SIZE);
  }

  protected final void setDefaultFont(PdfContentByte directContent) {
    directContent.setFontAndSize(getBaseFont(), IVoidStateFormatConstants.FONT_SIZE);
  }

  protected final int getDefaultTextWidth(String text) {
    return (int) getBaseFont().getWidthPoint(text, IVoidStateFormatConstants.FONT_SIZE);
  }

  protected final int getCommentTextWidth(String text) {
    return (int) getBaseFont().getWidthPoint(text, IVoidStateFormatConstants.COMMENT_FONT_SIZE);
  }

  protected final void setFillColorBlack(PdfContentByte directContent) {
    directContent.setRGBColorFill(0, 0, 0);
  }

  protected final void drawMissingTextLine(PdfContentByte directContent, Position position, float length) {
    setFillColorBlack(directContent);
    directContent.setLineWidth(0);
    directContent.moveTo(position.x, position.y);
    directContent.lineTo(position.x + length, position.y);
    directContent.stroke();
  }

  protected final void drawComment(PdfContentByte directContent, String text, Position position, int alignment) {
    setFillColorBlack(directContent);
    setCommentFont(directContent);
    directContent.setLineWidth(0);
    drawText(directContent, text, position, alignment, 0);
  }

  protected final void drawText(PdfContentByte directContent, String text, Position position, int alignment) {
    addText(directContent, text, position, alignment, 0);
  }

  protected final void drawVerticalText(PdfContentByte directContent, String text, Position position, int alignment) {
    addText(directContent, text, position, alignment, 90);
  }

  private void addText(PdfContentByte directContent, String text, Position position, int alignment, int rotation) {
    initDirectContentForText(directContent);
    drawText(directContent, text, position, alignment, rotation);
  }

  private void drawText(PdfContentByte directContent, String text, Position position, int alignment, int rotation) {
    directContent.beginText();
    directContent.showTextAlignedKerned(alignment, text, position.x, position.y, rotation);
    directContent.endText();
  }

  protected final void drawLabelledContent(
      PdfContentByte directContent,
      String label,
      String content,
      Position position,
      float width) {
    initDirectContentForText(directContent);
    directContent.beginText();
    directContent.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, label, position.x, position.y, 0);
    float labelWidth = getDefaultTextWidth(label);
    float contentX = position.x + labelWidth + 2;
    if (StringUtilities.isNullOrTrimEmpty(content)) {
      directContent.endText();
      float lineWidth = position.x + width - contentX;
      drawMissingTextLine(directContent, new Position(contentX, position.y), lineWidth);
    }
    else {
      directContent.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, content, contentX, position.y, 0);
      directContent.endText();
    }
  }

  private void initDirectContentForText(PdfContentByte directContent) {
    setFillColorBlack(directContent);
    setDefaultFont(directContent);
    directContent.setLineWidth(0);
  }

    protected void encodeTextWithReducedLineHeight(PdfContentByte directContent, Bounds textBounds, Phrase phrase) throws DocumentException {
        PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, IVoidStateFormatConstants.LINE_HEIGHT - 2);
    }
}