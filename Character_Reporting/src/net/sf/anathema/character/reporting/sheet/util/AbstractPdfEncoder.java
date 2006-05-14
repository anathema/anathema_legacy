package net.sf.anathema.character.reporting.sheet.util;

import java.awt.Point;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public abstract class AbstractPdfEncoder {

  private static final int SUBSECTION_FONT_SIZE = 8;

  protected abstract BaseFont getBaseFont();

  protected final void setCommentFont(PdfContentByte directContent) {
    directContent.setFontAndSize(getBaseFont(), IVoidStateFormatConstants.FONT_SIZE - 2);
  }

  protected final void setDefaultFont(PdfContentByte directContent) {
    directContent.setFontAndSize(getBaseFont(), IVoidStateFormatConstants.FONT_SIZE);
  }

  protected final void setSubsectionFont(PdfContentByte directContent) {
    int fontSize = SUBSECTION_FONT_SIZE;
    directContent.setFontAndSize(getBaseFont(), fontSize);
  }

  protected final int drawSubsectionHeader(PdfContentByte directContent, String text, Point position, int width) {
    setSubsectionFont(directContent);
    drawText(directContent, text, new Point(position.x + width / 2, position.y), PdfContentByte.ALIGN_CENTER);
    return (int) (SUBSECTION_FONT_SIZE * 1.5);
  }

  protected final int getDefaultTextWidth(String text) {
    return (int) getBaseFont().getWidthPoint(text, IVoidStateFormatConstants.FONT_SIZE);
  }

  protected final void setFillColorWhite(PdfContentByte directContent) {
    directContent.setRGBColorFill(255, 255, 255);
  }

  protected final void setFillColorBlack(PdfContentByte directContent) {
    directContent.setRGBColorFill(0, 0, 0);
  }

  protected final void drawMissingTextLine(PdfContentByte directContent, Point position, int length) {
    setFillColorBlack(directContent);
    directContent.setLineWidth(0);
    directContent.moveTo(position.x, position.y);
    directContent.lineTo(position.x + length, position.y);
    directContent.stroke();
  }

  protected final void drawComment(PdfContentByte directContent, String text, Point position, int alignment) {
    setFillColorBlack(directContent);
    setCommentFont(directContent);
    directContent.setLineWidth(0);
    drawText(directContent, text, position, alignment, 0);
  }

  protected final void drawText(PdfContentByte directContent, String text, Point position, int alignment) {
    addText(directContent, text, position, alignment, 0);
  }

  protected final void drawVerticalText(PdfContentByte directContent, String text, Point position, int alignment) {
    addText(directContent, text, position, alignment, 90);
  }

  private void addText(PdfContentByte directContent, String text, Point position, int alignment, int rotation) {
    initDirectContentForText(directContent);
    drawText(directContent, text, position, alignment, rotation);
  }

  private void drawText(PdfContentByte directContent, String text, Point position, int alignment, int rotation) {
    directContent.beginText();
    directContent.showTextAlignedKerned(alignment, text, position.x, position.y, rotation);
    directContent.endText();
  }

  protected final void drawLabelledContent(
      PdfContentByte directContent,
      String label,
      String content,
      Point position,
      int width) {
    initDirectContentForText(directContent);
    directContent.beginText();
    directContent.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, label, position.x, position.y, 0);
    int labelWidth = getDefaultTextWidth(label);
    int contentX = (position.x + labelWidth + 2);
    if (StringUtilities.isNullOrTrimEmpty(content)) {
      directContent.endText();
      int lineWidth = (position.x + width) - contentX;
      drawMissingTextLine(directContent, new Point(contentX, position.y), lineWidth);
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
}