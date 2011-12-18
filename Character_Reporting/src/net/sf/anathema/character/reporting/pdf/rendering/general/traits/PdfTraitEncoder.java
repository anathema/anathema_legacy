package net.sf.anathema.character.reporting.pdf.rendering.general.traits;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

import java.awt.*;

public class PdfTraitEncoder extends AbstractPdfEncoder {

  private class Dot implements IShape {
    public void encode(PdfContentByte directContent, Position lowerLeft, int dotIndex, int value) {
      directContent.arc(lowerLeft.x, lowerLeft.y, lowerLeft.x + dotSize, lowerLeft.y + dotSize, 0, 360);
      commitShape(directContent, dotIndex < value);
    }
  }

  private interface IShape {
    public void encode(PdfContentByte directContent, Position lowerLeft, int dotIndex, int value);
  }

  private class Square implements IShape {
    public void encode(PdfContentByte directContent, Position lowerLeft, int dotIndex, int value) {
      directContent.rectangle(lowerLeft.x, lowerLeft.y, dotSize, dotSize);
      commitShape(directContent, dotIndex < value);
    }
  }

  private static final int SMALL_DOT_SPACING = 2;
  public static final int DOT_PADDING = 2;

  public static PdfTraitEncoder createLargeTraitEncoder(BaseFont baseFont) {
    return new PdfTraitEncoder(baseFont, 10 + 2 * DOT_PADDING, 10);
  }

  public static PdfTraitEncoder createMediumTraitEncoder(BaseFont baseFont) {
    return new PdfTraitEncoder(baseFont, 8 + 2 * DOT_PADDING, 8);
  }

  public static PdfTraitEncoder createSmallTraitEncoder(BaseFont baseFont) {
    return new PdfTraitEncoder(baseFont, 6 + 2 * DOT_PADDING, 6);
  }

  private final float height;
  private final int dotSize;
  private final BaseFont baseFont;

  private PdfTraitEncoder(BaseFont baseFont, float height, int dotSize) {
    this.baseFont = baseFont;
    this.height = height;
    this.dotSize = dotSize;
  }

  private void commitShape(PdfContentByte directContent, boolean isFilled) {
    if (isFilled) {
      directContent.fillStroke();
    }
    else {
      directContent.stroke();
    }
  }

  public float encodeDotsCenteredAndUngrouped(PdfContentByte directContent, Position position, float width, int value, int dotCount) {
    return encodeShapeCenteredAndUngrouped(directContent, position, width, value, dotCount, new Dot());
  }

  private int encodeGroupedDots(PdfContentByte directContent, Position position, float width, int value, int dotCount, final int dotSpacing) {
    int groupSpacing = dotCount > 5 ? dotSize / 2 : 0;
    int spacecount = (int) Math.ceil((double) dotCount / 5);
    for (int dot = 0; dot < dotCount; dot++) {
      if (dot % 5 == 0) {
        spacecount--;
      }
      float currentGroupingSpace = spacecount * groupSpacing;
      float rightEdgeX = position.x + width;
      float spaceNeededRight = currentGroupingSpace + (dotCount - dot) * (dotSize + dotSpacing);
      Position lowerLeft = new Position(rightEdgeX - spaceNeededRight, position.y);
      new Dot().encode(directContent, lowerLeft, dot, value);
    }
    return dotCount * dotSize + (dotCount - 1) * dotSpacing + groupSpacing;
  }

  private float encodeShapeCenteredAndUngrouped(PdfContentByte directContent, Position position, float width, int value, int dotCount,
    IShape shape) {
    initDirectContent(directContent);
    int dotWidth = dotCount * dotSize;
    final float dotSpacing = (width - dotWidth) / (dotCount + 1);
    float neededWidth = dotWidth + (dotCount - 1) * dotSpacing;
    float leftDotX = position.x + (width - neededWidth) / 2;
    for (int dot = 0; dot < dotCount; dot++) {
      shape.encode(directContent, new Position(leftDotX, position.y), dot, value);
      leftDotX += dotSize + dotSpacing;
    }
    return height;
  }

  public float encodeSquaresCenteredAndUngrouped(PdfContentByte directContent, Position position, float width, int value, int dotCount) {
    return encodeShapeCenteredAndUngrouped(directContent, position, width, value, dotCount, new Square());
  }

  public float encodeWithLine(PdfContentByte directContent, Position position, float width, int value, int dotCount) {
    initDirectContent(directContent);
    float dotsWidth = encodeGroupedDots(directContent, position, width, value, dotCount, SMALL_DOT_SPACING);
    drawMissingTextLine(directContent, position, width - dotsWidth - 5);
    return height;
  }

  public float encodeWithText(PdfContentByte directContent, String text, Position position, float width, int value, int dotCount) {
    initDirectContent(directContent);
    directContent.beginText();
    directContent.showTextAligned(PdfContentByte.ALIGN_LEFT, text, position.x, position.y, 0);
    directContent.endText();
    encodeGroupedDots(directContent, position, width, value, dotCount, SMALL_DOT_SPACING);
    return height;
  }

  public float encodeWithTextAndRectangle(PdfContentByte directContent, String text, Position position, float width, int value, boolean favored,
    int dotCount) {
    initDirectContent(directContent);
    directContent.rectangle(position.x, position.y, dotSize, dotSize);
    commitShape(directContent, favored);
    int squareWidth = dotSize + 2;
    Position usualTraitPosition = new Position(position.x + squareWidth, position.y);
    return encodeWithText(directContent, text, usualTraitPosition, width - squareWidth, value, dotCount);
  }

  public float encodeWithExcellencies(PdfContentByte directContent, String text, Position position, float width, int value, boolean favored,
    boolean[] excellencyLearned, int dotCount) {
    initDirectContent(directContent);
    for (int i = excellencyLearned.length; i > 0; i--) {
      String label = Integer.toString(i);
      float labelWidth = getBaseFont().getWidthPoint(label, IVoidStateFormatConstants.FONT_SIZE);

      PdfTemplate template = directContent.createTemplate(labelWidth, height);
      initDirectContent(template);
      if (excellencyLearned[i - 1]) {
        template.setColorFill(Color.BLACK);
      }
      else {
        template.setColorFill(Color.LIGHT_GRAY);
      }
      template.beginText();
      template.showTextAligned(PdfContentByte.ALIGN_LEFT, label, 0, 0, 0);
      template.endText();

      directContent.addTemplate(template, position.x + width - labelWidth, position.y);
      width -= labelWidth + SMALL_DOT_SPACING;
    }

    return encodeWithTextAndRectangle(directContent, text, position, width, value, favored, dotCount);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public float getTraitHeight() {
    return height;
  }

  private void initDirectContent(PdfContentByte directContent) {
    setDefaultFont(directContent);
    setFillColorBlack(directContent);
    directContent.setLineWidth(0.8f);
  }
}
