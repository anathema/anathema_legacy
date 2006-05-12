package net.sf.anathema.development.character.reporting;

import java.awt.Point;

import com.lowagie.text.pdf.PdfContentByte;

public class PdfTraitEncoder {

  public static final int MAX_DOT_COUNT = 7;
  public static final int DEFAULT_HEIGHT = 12;

  public static final int DOT_SPACING = 1;
  public static final int PADDING = 8;
  private final int height;
  private final int fontSize;
  protected final int dotSize;

  public PdfTraitEncoder() {
    this(8);
  }

  public PdfTraitEncoder(int dotsSize) {
    this(dotsSize, DEFAULT_HEIGHT, DEFAULT_HEIGHT - 4);
  }

  public PdfTraitEncoder(int dotsSize, int lineHeight, int fontSize) {
    this.dotSize = dotsSize;
    this.height = lineHeight;
    this.fontSize = fontSize;
  }

  public int encodeWithText(
      PdfContentByte directContent,
      String text,
      Point position,
      int width,
      int value,
      int dotCount) {
    boolean putBlankAfterFifthDot = true;
    int groupingSpace = 0;
    for (int dot = 0; dot < dotCount; dot++) {
      if (putBlankAfterFifthDot && (dot % 5 == 0) && (dot + 1 < dotCount)) {
        groupingSpace += dotSize / 2;
      }
      int rightEdgeX = position.x + width;
      int spaceNeededRight = groupingSpace + ((dotCount - dot) * (dotSize + DOT_SPACING));
      // todo vom (12.05.2006) (sieroux): Ellipse zeichnen
    }
    return height;
  }
}