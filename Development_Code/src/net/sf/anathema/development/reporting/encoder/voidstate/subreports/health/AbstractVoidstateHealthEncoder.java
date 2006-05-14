package net.sf.anathema.development.reporting.encoder.voidstate.subreports.health;

import java.awt.Rectangle;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.framework.reporting.parameters.HealthParameterUtilities;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.health.IHealthLevelTypeVisitor;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.HealthRectangleEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SlashSeparatedLineEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;

import org.dom4j.Element;

public abstract class AbstractVoidstateHealthEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  private static final int HEALTH_RECT_PADDING = 3;
  private static final int HEALTH_LINE_PADDING = LINE_HEIGHT / 2 - 3;
  private static final int TEXT_Y_OFFSET = 3;
  private final SlashSeparatedLineEncoder soakLineEncoder = new SlashSeparatedLineEncoder();
  private final HealthRectangleEncoder healthRectangleEncoder = new HealthRectangleEncoder();

  public static Rectangle calculateBounds() {
    return new Rectangle(162, 109);
  }

  protected final HealthRectangleEncoder getHealthRectangleEncoder() {
    return healthRectangleEncoder;
  }

  protected final void encodeSoak(Element parent, int width, boolean addSpareLine) {
    int smallLineHeight = LINE_HEIGHT - 2;
    int yOffset = 0;
    addTextElement(parent, quotify("Base Soak (B/L)"), FONT_SIZE, VALUE_LEFT, 0, 0, width, smallLineHeight);
    int soakLineWidth = getSoakLineWidth();
    int lineX = getLineStartX();
    addTextElement(
        parent,
        ParameterUtilities.parameterString(HealthParameterUtilities.getSoakParameter(HealthType.Bashing))
            + methodCall("toString"),
        FONT_SIZE,
        VALUE_CENTER,
        lineX,
        0,
        soakLineWidth,
        smallLineHeight);
    addTextElement(parent, quotify("/"), FONT_SIZE - 1, VALUE_LEFT, lineX + soakLineWidth, 0, 5, smallLineHeight);
    addTextElement(
        parent,
        ParameterUtilities.parameterString(HealthParameterUtilities.getSoakParameter(HealthType.Lethal))
            + methodCall("toString"),
        FONT_SIZE,
        VALUE_CENTER,
        lineX + soakLineWidth + 2,
        0,
        soakLineWidth,
        smallLineHeight);
    yOffset += smallLineHeight;
    addTextElement(parent, quotify("+ Armour (B/L/A)"), FONT_SIZE, VALUE_LEFT, 0, yOffset, width, smallLineHeight);
    soakLineEncoder.encodeSlashSeparatedLines(parent, lineX, yOffset - 1, smallLineHeight, 2, soakLineWidth);
    yOffset += smallLineHeight;
    if (addSpareLine) {
      addTextElement(
          parent,
          quotify("+ ____________ (B/L/A)"),
          FONT_SIZE,
          VALUE_LEFT,
          0,
          0 + yOffset,
          width,
          smallLineHeight);
      soakLineEncoder.encodeSlashSeparatedLines(parent, lineX, yOffset - 1, smallLineHeight, 2, soakLineWidth);
      yOffset += smallLineHeight;
    }
    addTextElement(parent, quotify("= Total (B/L/A)"), FONT_SIZE, VALUE_LEFT, 0, yOffset, width, smallLineHeight);
    soakLineEncoder.encodeSlashSeparatedLines(parent, lineX, yOffset - 1, smallLineHeight, 2, soakLineWidth);
  }

  protected abstract int getLineStartX();

  protected abstract int getSoakLineWidth();

  protected int encodeHealthRectangleBox(Element parent, int y, int width, int height, int oneRows, int twoRows) {
    int x = 0;
    addNormalLineElement(parent, new Rectangle(x, y, width, 0));
    addNormalLineElement(parent, new Rectangle(x, y, 0, height));
    addNormalLineElement(parent, new Rectangle(width, y, 0, height));
    addNormalLineElement(parent, new Rectangle(x, y + height - 1, width, 0));
    y += HEALTH_LINE_PADDING + 1;
    int naturalRightSquareX = width - (width * 5) / 12 + 2 * (HEALTH_RECT_SIZE + HEALTH_RECT_PADDING);
    int oxBodyRightSquareX = naturalRightSquareX - 2 * (HEALTH_RECT_SIZE + HEALTH_RECT_PADDING);
    int movementTextX = width - 45;
    x += 8;
    encodeLevelZeroHealthLevels(parent, y, x, width, naturalRightSquareX, oxBodyRightSquareX, movementTextX);
    y += HEALTH_RECT_SIZE + HEALTH_LINE_PADDING;
    encodeLevelOneHealthLevels(parent, y, x, width, naturalRightSquareX, oxBodyRightSquareX, movementTextX, oneRows);
    y += oneRows * (HEALTH_RECT_SIZE + HEALTH_LINE_PADDING);
    encodeLevelTwoHealthLevels(parent, y, x, width, naturalRightSquareX, oxBodyRightSquareX, movementTextX, twoRows);
    y += twoRows * (HEALTH_RECT_SIZE + HEALTH_LINE_PADDING);
    encodeSingleBoxHealthLevel(parent, y, x, width, naturalRightSquareX, movementTextX, "-4", HealthLevelType.FOUR);
    y += HEALTH_RECT_SIZE + HEALTH_LINE_PADDING;
    encodeSingleBoxHealthLevel(
        parent,
        y,
        x,
        width,
        naturalRightSquareX,
        movementTextX,
        "Incapacitated",
        HealthLevelType.INCAPACITATED);
    return height;
  }

  private void encodeSingleBoxHealthLevel(
      final Element parent,
      final int y,
      final int x,
      int width,
      int naturalRightSquareX,
      int movementTextX,
      String healthLevelName,
      final HealthLevelType healthLevelType) {
    addTextElement(parent, quotify(healthLevelName), FONT_SIZE, VALUE_LEFT, x, y - TEXT_Y_OFFSET, width, LINE_HEIGHT);
    addPainToleranceIfApplicable(parent, y, x, TEXT_Y_OFFSET, healthLevelType);
    encodeHealthRectPair(parent, naturalRightSquareX, y, 0, healthLevelType);
    encodeMovementValue(parent, movementTextX, y - TEXT_Y_OFFSET, healthLevelType);
  }

  private void addPainToleranceIfApplicable(
      final Element parent,
      final int y,
      final int x,
      final int textYOffset,
      final HealthLevelType healthLevelType) {
    healthLevelType.accept(new IHealthLevelTypeVisitor() {
      public void visitZero(HealthLevelType type) {
        // Nothing to do
      }

      public void visitOne(HealthLevelType type) {
        addPainToleranceText(
            parent,
            healthLevelType,
            x + 10,
            y - textYOffset,
            ICharacterReportConstants.PAIN_TOLERANCE_1);
      }

      public void visitTwo(HealthLevelType type) {
        addPainToleranceText(
            parent,
            healthLevelType,
            x + 10,
            y - textYOffset,
            ICharacterReportConstants.PAIN_TOLERANCE_2);
      }

      public void visitFour(HealthLevelType type) {
        addPainToleranceText(
            parent,
            healthLevelType,
            x + 10,
            y - textYOffset,
            ICharacterReportConstants.PAIN_TOLERANCE_4);
      }

      public void visitIncapacitated(HealthLevelType type) {
        // Nothing to do
      }
    });
  }

  private void addPainToleranceText(
      Element parent,
      HealthLevelType healthLevelType,
      int x,
      int y,
      String toleranceParameter) {
    ITextPart[] painTolerancePart = new ITextPart[] {
        new TextPart("(-", DEFAULT_TEXT_FORMAT),
        new TextPart("\"+$P{" + toleranceParameter + "}.intValue()+\"", DEFAULT_TEXT_FORMAT),
        new TextPart(")", DEFAULT_TEXT_FORMAT) };
    Element element = addStyledTextElement(parent, painTolerancePart, FONT_SIZE, VALUE_LEFT, x, y, 40, LINE_HEIGHT);
    addPrintWhenExpression(element, ParameterUtilities.parameterString(toleranceParameter)
        + methodCall("intValue")
        + "<"
        + healthLevelType.getId());
  }

  private void encodeLevelTwoHealthLevels(
      Element parent,
      int y,
      int x,
      int width,
      int naturalRightSquareX,
      int oxBodyRightSquareX,
      int movementTextX,
      int rowCount) {
    encodeMultipleHealthLevelRows(
        parent,
        y,
        x,
        width,
        naturalRightSquareX,
        oxBodyRightSquareX,
        movementTextX,
        HealthLevelType.TWO,
        "-2",
        rowCount);
  }

  private void encodeLevelOneHealthLevels(
      Element parent,
      int y,
      int x,
      int width,
      int naturalRightSquareX,
      int oxBodyRightSquareX,
      int movementTextX,
      int rowCount) {
    encodeMultipleHealthLevelRows(
        parent,
        y,
        x,
        width,
        naturalRightSquareX,
        oxBodyRightSquareX,
        movementTextX,
        HealthLevelType.ONE,
        "-1",
        rowCount);
  }

  private void encodeMultipleHealthLevelRows(
      Element parent,
      int y,
      int x,
      int width,
      int naturalRightSquareX,
      int oxBodyRightSquareX,
      int movementTextX,
      HealthLevelType healthLevelType,
      String healthLevelName,
      int rowCount) {
    int levelsAlreadyEncoded = encodeInitialHealthLevelRow(
        parent,
        y,
        x,
        width,
        naturalRightSquareX,
        oxBodyRightSquareX,
        movementTextX,
        healthLevelType,
        healthLevelName,
        2);
    for (int index = 1; index < rowCount; index++) {
      y += HEALTH_RECT_SIZE + HEALTH_LINE_PADDING;
      levelsAlreadyEncoded += encodeHealthRectangleRow(
          parent,
          y,
          oxBodyRightSquareX,
          healthLevelType,
          levelsAlreadyEncoded);
    }
  }

  private int encodeInitialHealthLevelRow(
      Element parent,
      int y,
      int x,
      int width,
      int naturalRightSquareX,
      int oxBodyRightSquareX,
      int movementTextX,
      HealthLevelType type,
      String healthLevelName,
      int naturalHealthLevels) {
    addTextElement(parent, quotify(healthLevelName), FONT_SIZE, VALUE_LEFT, x, y - TEXT_Y_OFFSET, width, LINE_HEIGHT);
    addPainToleranceIfApplicable(parent, y, x, TEXT_Y_OFFSET, type);
    for (int index = 0; index < naturalHealthLevels; index++) {
      encodeHealthRectPair(
          parent,
          naturalRightSquareX - index * (HEALTH_RECT_PADDING + HEALTH_RECT_SIZE),
          y,
          index,
          type);
    }
    int additionalLevels = encodeHealthRectangleRow(parent, y, oxBodyRightSquareX, type, naturalHealthLevels);
    encodeMovementValue(parent, movementTextX, y - TEXT_Y_OFFSET, type);
    return naturalHealthLevels + additionalLevels;
  }

  private void encodeLevelZeroHealthLevels(
      Element parent,
      int y,
      int x,
      int width,
      int naturalRightSquareX,
      int oxBodyRightSquareX,
      int movementTextX) {
    encodeInitialHealthLevelRow(
        parent,
        y,
        x,
        width,
        naturalRightSquareX,
        oxBodyRightSquareX,
        movementTextX,
        HealthLevelType.ZERO,
        "-0",
        1);
  }

  /**
   * @param healthLevelOffset is used to indicate the number of boxes already encoded before begining this row.
   * @return The number of health level boxes encoded.
   */
  private int encodeHealthRectangleRow(
      Element parent,
      int y,
      int oxBodyRightSquareX,
      HealthLevelType healthLevelType,
      int healthLevelOffset) {
    int boxesToDo = 7;
    for (int rectangleCount = 0; rectangleCount < boxesToDo; rectangleCount++) {
      int squareX = oxBodyRightSquareX - rectangleCount * (HEALTH_RECT_PADDING + HEALTH_RECT_SIZE);
      encodeHealthRectPair(parent, squareX, y, rectangleCount + healthLevelOffset, healthLevelType);
    }
    return boxesToDo;
  }

  private void encodeMovementValue(Element parent, int x, int y, HealthLevelType healthLevelType) {
    final String[] movementValue = new String[1];
    healthLevelType.accept(new IHealthLevelTypeVisitor() {
      public void visitZero(HealthLevelType type) {
        movementValue[0] = ICharacterReportConstants.MOVE_AT_HEALTH_0;
      }

      public void visitOne(HealthLevelType type) {
        movementValue[0] = ICharacterReportConstants.MOVE_AT_HEALTH_1;
      }

      public void visitTwo(HealthLevelType type) {
        movementValue[0] = ICharacterReportConstants.MOVE_AT_HEALTH_2;
      }

      public void visitFour(HealthLevelType type) {
        movementValue[0] = ICharacterReportConstants.MOVE_AT_HEALTH_4;
      }

      public void visitIncapacitated(HealthLevelType type) {
        movementValue[0] = null;
      }
    });
    ITextPart[] movementPart;
    if (movementValue[0] != null) {
      movementPart = new ITextPart[] {
          new TextPart("\"+$P{" + movementValue[0] + "}.intValue()+\"", DEFAULT_TEXT_FORMAT),
          new TextPart(" yds/tn", new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1)) };
    }
    else {
      movementPart = new ITextPart[] { new TextPart("None", new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1)) };
    }
    addStyledTextElement(parent, movementPart, FONT_SIZE, VALUE_RIGHT, x, y, 40, LINE_HEIGHT);
  }

  private void encodeHealthRectPair(Element parent, int x, int y, int value, HealthLevelType healthLevelType) {
    String printWhenExpression = ParameterUtilities.parameterString(healthLevelType.getId())
        + methodCall("intValue")
        + "<="
        + value;
    getHealthRectangleEncoder().encodeHealthRect(parent, VALUE_COLOR_GRAY, x, y, printWhenExpression);
    getHealthRectangleEncoder().encodeHealthRect(parent, VALUE_COLOR_BLACK, x, y, "!(" + printWhenExpression + ")"); //$NON-NLS-1$ //$NON-NLS-2$
  }
}
