package net.sf.anathema.character.reporting.pdf.rendering.general.traits;

import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.GraphicsTemplate;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.shape.Dot;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.shape.Shape;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.shape.Square;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

import java.awt.*;

public class PdfTraitEncoder {

  public static final int LARGE_DOT_SIZE = 10;

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

  public float encodeDotsCenteredAndUngrouped(SheetGraphics graphics, Position position, float width, int value, int dotCount) {
    Dot dot = graphics.createDot(dotSize);
    return encodeShapeCenteredAndUngrouped(position, width, value, dotCount, dot);
  }

  private int encodeGroupedDots(SheetGraphics graphics, Position position, float width, int value, int dotCount, final int dotSpacing) {
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
      encodeShape(graphics.createDot(dotSize), lowerLeft, value, dot);
    }
    return dotCount * dotSize + (dotCount - 1) * dotSpacing + groupSpacing;
  }

  private float encodeShapeCenteredAndUngrouped(Position position, float width, int value, int dotCount, Shape shape) {
    int dotWidth = dotCount * dotSize;
    final float dotSpacing = (width - dotWidth) / (dotCount + 1);
    float neededWidth = dotWidth + (dotCount - 1) * dotSpacing;
    float leftDotX = position.x + (width - neededWidth) / 2;
    for (int dot = 0; dot < dotCount; dot++) {
      Position lowerLeft = new Position(leftDotX, position.y);
      encodeShape(shape, lowerLeft, value, dot);
      leftDotX += dotSize + dotSpacing;
    }
    return height;
  }

  public float encodeSquaresCenteredAndUngrouped(SheetGraphics graphics, Position position, float width, int value, int dotCount) {
    Square square = graphics.createSquare(dotSize);
    return encodeShapeCenteredAndUngrouped(position, width, value, dotCount, square);
  }

  public float encodeWithLine(SheetGraphics graphics, Position position, float width, int value, int dotCount) {
    float dotsWidth = encodeGroupedDots(graphics, position, width, value, dotCount, SMALL_DOT_SPACING);
    graphics.drawMissingTextLine(position, width - dotsWidth - 5);
    return height;
  }

  public float encodeWithText(SheetGraphics graphics, String text, Position position, float width, int value, int dotCount) {
    graphics.drawText(text, position, PdfContentByte.ALIGN_LEFT);
    encodeGroupedDots(graphics, position, width, value, dotCount, SMALL_DOT_SPACING);
    return height;
  }

  public float encodeWithTextAndRectangle(SheetGraphics graphics, String text, Position position, float width, int value, boolean favored,
    int dotCount) {
    Square square = graphics.createSquare(dotSize);
    encodeShape(square, position, favored);
    int squareWidth = dotSize + 2;
    Position usualTraitPosition = new Position(position.x + squareWidth, position.y);
    return encodeWithText(graphics, text, usualTraitPosition, width - squareWidth, value, dotCount);
  }

  public float encodeWithExcellencies(SheetGraphics graphics, String text, Position position, float width, int value, boolean favored,
    boolean[] excellencyLearned, int dotCount) {
    for (int i = excellencyLearned.length; i > 0; i--) {
      String label = Integer.toString(i);
      float labelWidth = graphics.getBaseFont().getWidthPoint(label, IVoidStateFormatConstants.FONT_SIZE);
      GraphicsTemplate encodingTemplate = graphics.createTemplate(labelWidth, height);
      SheetGraphics templateGraphics = encodingTemplate.getTemplateGraphics();
      boolean hasExcellencyLearned = excellencyLearned[i - 1];
      Position labelPosition = new Position(0, 0);
      if (hasExcellencyLearned) {
         templateGraphics.drawText(label, labelPosition, PdfContentByte.ALIGN_LEFT);
       }
      else {
         templateGraphics.drawDisabledText(label, labelPosition, PdfContentByte.ALIGN_LEFT);
      }
      float templateX = position.x + width - labelWidth;
      float templateY = position.y;
      encodingTemplate.addToParentAt(templateX, templateY);
      width -= labelWidth + SMALL_DOT_SPACING;
    }
    return encodeWithTextAndRectangle(graphics, text, position, width, value, favored, dotCount);
  }

  private void encodeShape(Shape shape, Position lowerLeft, int value, int dot) {
    encodeShape(shape, lowerLeft, dot < value);
  }

  private void encodeShape(Shape shape, Position lowerLeft, boolean filled) {
    if (filled) {
      shape.encodeFilled(lowerLeft);
    }
    else {
      shape.encodeOutlined(lowerLeft);
    }
  }

  public float getTraitHeight() {
    return height;
  }
}
