package net.sf.anathema.character.reporting.encoder;

import java.awt.Point;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public abstract class AbstractPdfEncoder {

  protected abstract BaseFont getBaseFont();

  protected final void setDefaultFont(PdfContentByte directContent) {
    directContent.setFontAndSize(getBaseFont(), IVoidStateFormatConstants.FONT_SIZE);
  }

  protected final float getDefaultTextWidth(String text) {
    return getBaseFont().getWidthPoint(text, IVoidStateFormatConstants.FONT_SIZE);
  }

  protected final void setFillColorWhite(PdfContentByte directContent) {
    directContent.setRGBColorFill(255, 255, 255);
  }

  protected final void setFillColorBlack(PdfContentByte directContent) {
    directContent.setRGBColorFill(0, 0, 0);
  }

  protected final void setLineWidthAHalf(PdfContentByte directContent) {
    directContent.setLineWidth(0.5f);
  }

  protected final void addText(PdfContentByte directContent, String text, Point position, int alignment) {
    initDirectContentForText(directContent);
    directContent.beginText();
    directContent.showTextAlignedKerned(alignment, text, position.x, position.y, 0);
    directContent.endText();
  }

  protected final void addLabelledContent(
      PdfContentByte directContent,
      String label,
      String content,
      Point position,
      int width) {
    initDirectContentForText(directContent);
    directContent.beginText();
    directContent.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, label, position.x, position.y, 0);
    float labelWidth = getDefaultTextWidth(label);
    float contentX = position.x + labelWidth + 2;
    if (StringUtilities.isNullOrTrimEmpty(content)) {
      directContent.endText();
      directContent.moveTo(contentX, position.y);
      directContent.lineTo(position.x + width, position.y);
      directContent.stroke();
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