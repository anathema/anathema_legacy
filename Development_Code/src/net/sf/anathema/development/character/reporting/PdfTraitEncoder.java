package net.sf.anathema.development.character.reporting;

import java.awt.Point;

import net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfTraitEncoder extends AbstractPdfEncoder {

  private final int dotSpacing = 2;
  private final int height = IVoidStateFormatConstants.LINE_HEIGHT;
  private final int dotSize = IVoidStateFormatConstants.SMALL_SYMBOL_HEIGHT - 1;
  private final BaseFont baseFont;

  public PdfTraitEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
  }

  public int encodeWithText(
      PdfContentByte directContent,
      String text,
      Point position,
      int width,
      int value,
      int dotCount) {
    setDefaultFont(directContent);
    setFillColorBlack(directContent);
    directContent.setLineWidth(0.8f);
    directContent.beginText();
    directContent.showTextAligned(PdfContentByte.ALIGN_LEFT, text, position.x, position.y, 0);
    directContent.endText();
    for (int dot = 0; dot < dotCount; dot++) {
      int groupingSpace = dot < 5 ? dotSize / 2 : 0;
      int rightEdgeX = position.x + width;
      int spaceNeededRight = groupingSpace + ((dotCount - dot) * (dotSize + dotSpacing));
      int leftDotX = rightEdgeX - spaceNeededRight;
      directContent.arc(leftDotX, position.y, leftDotX + dotSize, position.y + dotSize, 0, 360);
      if (dot < value) {
        directContent.fillStroke();
      }
      else {
        directContent.stroke();
      }
    }
    return height;
  }

  public int getTraitHeight() {
    return height;
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }
}