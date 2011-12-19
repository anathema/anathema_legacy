package net.sf.anathema.character.reporting.pdf.rendering.general.traits;

import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.GraphicsTemplate;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

import java.awt.*;

public class PdfTraitEncoder {

  public static final int LARGE_DOT_SIZE = 10;

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

  public static final int SMALL_DOT_SPACING = 2;
  public static final int DOT_PADDING = 2;

  public static PdfTraitEncoder createMediumTraitEncoder() {
    return createTraitEncoder(8);
  }

  public static PdfTraitEncoder createSmallTraitEncoder() {
    return createTraitEncoder(6);
  }

  public static PdfTraitEncoder createTraitEncoder(int dotSize) {
    return new PdfTraitEncoder(getEncodedHeight(dotSize), dotSize);
  }

  public static int getEncodedHeight(int dotSize) {
    return dotSize + 2 * DOT_PADDING;
  }

  private final int height;
  private final int dotSize;

  private PdfTraitEncoder(int height, int dotSize) {
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

  public float encodeDotsCenteredAndUngrouped(SheetGraphics graphics, Position position, float width, int value, int dotCount) {
    return encodeShapeCenteredAndUngrouped(graphics, position, width, value, dotCount, new Dot());
  }

  private int encodeGroupedDots(SheetGraphics graphics, Position position, float width, int value, int dotCount, final int dotSpacing) {
    PdfContentByte directContent = graphics.getDirectContent();
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

  private float encodeShapeCenteredAndUngrouped(SheetGraphics graphics, Position position, float width, int value, int dotCount, IShape shape) {
    PdfContentByte directContent = graphics.getDirectContent();
    initDirectContent(graphics);
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

  public float encodeSquaresCenteredAndUngrouped(SheetGraphics graphics, Position position, float width, int value, int dotCount) {
    return encodeShapeCenteredAndUngrouped(graphics, position, width, value, dotCount, new Square());
  }

  public float encodeWithLine(SheetGraphics graphics, Position position, float width, int value, int dotCount) {
    initDirectContent(graphics);
    float dotsWidth = encodeGroupedDots(graphics, position, width, value, dotCount, SMALL_DOT_SPACING);
    graphics.drawMissingTextLine(position, width - dotsWidth - 5);
    return height;
  }

  public float encodeWithText(SheetGraphics graphics, String text, Position position, float width, int value, int dotCount) {
    PdfContentByte directContent = graphics.getDirectContent();
    initDirectContent(graphics);
    directContent.beginText();
    directContent.showTextAligned(PdfContentByte.ALIGN_LEFT, text, position.x, position.y, 0);
    directContent.endText();
    encodeGroupedDots(graphics, position, width, value, dotCount, SMALL_DOT_SPACING);
    return height;
  }

  public float encodeWithTextAndRectangle(SheetGraphics graphics, String text, Position position, float width, int value, boolean favored,
    int dotCount) {
    PdfContentByte directContent = graphics.getDirectContent();
    initDirectContent(graphics);
    directContent.rectangle(position.x, position.y, dotSize, dotSize);
    commitShape(directContent, favored);
    int squareWidth = dotSize + 2;
    Position usualTraitPosition = new Position(position.x + squareWidth, position.y);
    return encodeWithText(graphics, text, usualTraitPosition, width - squareWidth, value, dotCount);
  }

  public float encodeWithExcellencies(SheetGraphics graphics, String text, Position position, float width, int value, boolean favored,
    boolean[] excellencyLearned, int dotCount) {
    initDirectContent(graphics);
    for (int i = excellencyLearned.length; i > 0; i--) {
      String label = Integer.toString(i);
      float labelWidth = graphics.getBaseFont().getWidthPoint(label, IVoidStateFormatConstants.FONT_SIZE);
      GraphicsTemplate encodingTemplate = graphics.createTemplate(labelWidth, height);
      PdfTemplate template =  encodingTemplate.getTemplate();
      encodingTemplate.getTemplateGraphics().initDirectContentForGraphics();
      if (excellencyLearned[i - 1]) {
        template.setColorFill(Color.BLACK);
      }
      else {
        template.setColorFill(Color.LIGHT_GRAY);
      }
      template.beginText();
      template.showTextAligned(PdfContentByte.ALIGN_LEFT, label, 0, 0, 0);
      template.endText();

      float templateX = position.x + width - labelWidth;
      float templateY = position.y;
      encodingTemplate.addToParentAt(templateX, templateY);
      width -= labelWidth + SMALL_DOT_SPACING;
    }
    return encodeWithTextAndRectangle(graphics, text, position, width, value, favored, dotCount);
  }

  public float getTraitHeight() {
    return height;
  }

  private void initDirectContent(SheetGraphics graphics) {
    graphics.initDirectContentForGraphics();;
  }
}
