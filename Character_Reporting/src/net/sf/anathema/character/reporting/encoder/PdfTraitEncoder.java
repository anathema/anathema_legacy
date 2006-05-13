package net.sf.anathema.character.reporting.encoder;

import java.awt.Point;

import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfTraitEncoder extends AbstractPdfEncoder {

  public static PdfTraitEncoder createSmallTraitEncoder(BaseFont baseFont) {
    return new PdfTraitEncoder(
        baseFont,
        IVoidStateFormatConstants.LINE_HEIGHT,
        IVoidStateFormatConstants.SMALL_SYMBOL_HEIGHT - 1);
  }

  public static PdfTraitEncoder createLargeTraitEncoder(BaseFont baseFont) {
    return new PdfTraitEncoder(baseFont, 14, 11);
  }

  private final int height;
  private final int dotSize;
  private final BaseFont baseFont;

  private PdfTraitEncoder(BaseFont baseFont, int height, int dotSize) {
    this.baseFont = baseFont;
    this.height = height;
    this.dotSize = dotSize;
  }

  public int encodeWithText(
      PdfContentByte directContent,
      String text,
      Point position,
      int width,
      int value,
      int dotCount) {
    final int dotSpacing = 2;
    initDirectContent(directContent);
    directContent.beginText();
    directContent.showTextAligned(PdfContentByte.ALIGN_LEFT, text, position.x, position.y, 0);
    directContent.endText();
    for (int dot = 0; dot < dotCount; dot++) {
      int groupingSpace = dot < 5 && dotCount > 5 ? dotSize / 2 : 0;
      int rightEdgeX = position.x + width;
      int spaceNeededRight = groupingSpace + ((dotCount - dot) * (dotSize + dotSpacing));
      Point lowerLeft = new Point(rightEdgeX - spaceNeededRight, position.y);
      encodeDot(directContent, lowerLeft, dot, value);
    }
    return height;
  }

  private void initDirectContent(PdfContentByte directContent) {
    setDefaultFont(directContent);
    setFillColorBlack(directContent);
    directContent.setLineWidth(0.8f);
  }

  public int encodeCenteredAndUngrouped(PdfContentByte directContent, Point position, int width, int value, int dotCount) {
    initDirectContent(directContent);
    int dotWidth = dotCount * dotSize;
    final int dotSpacing = (width - dotWidth) / (dotCount + 1);
    int neededWidth = dotWidth + (dotCount - 1) * dotSpacing;
    int leftDotX = position.x + (width - neededWidth) / 2;
    for (int dot = 0; dot < dotCount; dot++) {
      encodeDot(directContent, new Point(leftDotX, position.y), dot, value);
      leftDotX += dotSize + dotSpacing;
    }
    return height;
  }

  private void encodeDot(PdfContentByte directContent, Point lowerLeft, int dotIndex, int value) {
    directContent.arc(lowerLeft.x, lowerLeft.y, lowerLeft.x + dotSize, lowerLeft.y + dotSize, 0, 360);
    if (dotIndex < value) {
      directContent.fillStroke();
    }
    else {
      directContent.stroke();
    }
  }

  public int getTraitHeight() {
    return height;
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }
}