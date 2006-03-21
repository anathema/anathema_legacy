package net.sf.anathema.development.reporting.util;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.framework.reporting.datasource.BackgroundsDataSource;
import net.sf.anathema.character.generic.framework.reporting.datasource.ITraitDataSource;
import net.sf.anathema.character.generic.framework.reporting.datasource.SpecialtiesDataSource;

import org.dom4j.Element;

public class TraitEncoder extends AbstractJasperEncoder {

  public static final int MAX_DOT_COUNT = 7;
  public static final int DEFAULT_HEIGHT = 12;

  public static final int DOT_SPACING = 1;
  public static final int PADDING = 8;
  private final int height;
  private final int fontSize;
  protected final int dotsSize;

  public TraitEncoder() {
    this(8);
  }

  public TraitEncoder(int dotsSize) {
    this(dotsSize, DEFAULT_HEIGHT, DEFAULT_HEIGHT - 4);
  }

  public TraitEncoder(int dotsSize, int lineHeight, int fontSize) {
    this.dotsSize = dotsSize;
    this.height = lineHeight;
    this.fontSize = fontSize;
  }

  public int getDotSize() {
    return dotsSize;
  }

  public int encodeWithText(Element parent, String parameterName, int x, int y, int width, int dotCount) {
    int textWidth = width - getBackgroundValueWidth(dotCount) - ((dotCount / 5) + 1) * (dotsSize / 2);
    addTextElement(parent, quotify(parameterName), fontSize, "Left", x, y, textWidth, height); //$NON-NLS-1$
    return encodeWithoutText(parent, parameterName, x + textWidth, y, dotCount, true);
  }

  public int encodeWithoutText(
      Element parent,
      String parameterName,
      int x,
      int y,
      int dotCount,
      boolean putBlankAfterFifthDot) {
    return encodeWithoutText(parent, parameterName, x, y, dotCount, dotsSize, DOT_SPACING, putBlankAfterFifthDot);
  }

  public int encodeWithoutText(
      Element parent,
      String parameterName,
      int x,
      int y,
      int dotCount,
      int dotSize,
      int spacing,
      boolean putBlankAfterFifthDot) {
    int groupingSpace = 0;
    for (int dot = 0; dot < dotCount; dot++) {
      if (putBlankAfterFifthDot && (dot % 5 == 0) && (dot + 1 < dotCount)) {
        groupingSpace += dotSize / 2;
      }
      addEllipsePair(
          parent,
          x + groupingSpace + (dot * (dotSize + spacing)),
          height - dotSize + y,
          parameterName,
          dot,
          dotSize);
    }
    return height;
  }

  public int encodeWithRectangle(
      Element parent,
      String booleanParameterName,
      String intParameterName,
      int x,
      int y,
      int width,
      int dotCount) {
    int textWidth = width - (dotsSize + 1) * (dotCount + 1) - PADDING;
    addRectanglePair(parent, x, (height - dotsSize - 1) + y, booleanParameterName);
    x += dotsSize + 2;
    addTextElement(parent, quotify(intParameterName), fontSize, "Left", x, y, textWidth, height); //$NON-NLS-1$
    encodeWithoutText(parent, intParameterName, x + textWidth + PADDING, y, dotCount, true);
    return height;
  }

  public int calculateVoidstateDotWidth(int dotCount) {
    return (dotsSize + 1) * (dotCount + 2) + PADDING + ((dotCount / 5) + 1) * (dotsSize / 2);
  }

  public Element[] encodeCross(Element parent, int x, int y) {
    Element[] star = new Element[2];
    star[0] = addNormalLineElement(parent, new Rectangle(x + 1, y + dotsSize / 2, dotsSize - 2, 0));
    star[1] = addNormalLineElement(parent, new Rectangle(x + dotsSize / 2, y + 1, 0, dotsSize - 2));
    return star;
  }

  public int encodeSpecialtyRow(Element parent, int currentRow, int x, int y, int width, int dotCount) {
    int textWidth = width - getBackgroundValueWidth(dotCount) - PADDING;
    String dataSource = ParameterUtilities.parameterString(ICharacterReportConstants.SPECIALTIES_DATA_SOURCE);
    String printName = dataSource
        + methodCall("getValue", new Object[] { currentRow, quotify(SpecialtiesDataSource.COLUMN_PRINT_NAME) }); //$NON-NLS-1$
    Element textReportElement = addTextElement(parent, printName, fontSize, "Left", x, y, textWidth, height); //$NON-NLS-1$
    String printWhenExpression = dataSource + methodCall("getRowCount") + " > " + currentRow; //$NON-NLS-1$ //$NON-NLS-2$
    textReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression);
    Element lineReportElement = addThinLineElement(parent, new Rectangle(x, y + height, textWidth, 0));
    lineReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA("!(" + printWhenExpression + ")"); //$NON-NLS-1$ //$NON-NLS-2$
    for (int dot = 0; dot < dotCount; dot++) {
      addSpecialtyEllipsePair(parent, currentRow, x + textWidth + (dot * (dotsSize + 1)) + PADDING, height
          - dotsSize
          + y, ICharacterReportConstants.SPECIALTIES_DATA_SOURCE, dot);
    }
    return height;
  }

  public int encodeNamedTraitDataSourceRow(
      Element parent,
      int currentRow,
      int x,
      int y,
      int width,
      int dotCount,
      int spacing,
      String dataSourceName) {
    int textWidth = width - getBackgroundValueWidth(dotCount) - spacing;
    String dataSource = ParameterUtilities.parameterString(dataSourceName);
    String printWhenExpression = dataSource + methodCall("getRowCount") + " > " + currentRow; //$NON-NLS-1$ //$NON-NLS-2$
    String printName = dataSource
        + methodCall("getValue", new Object[] { currentRow, quotify(ITraitDataSource.COLUMN_PRINT_NAME) }); //$NON-NLS-1$
    addLineOrText(parent, x, y, textWidth, printWhenExpression, printName);
    for (int dot = 0; dot < dotCount; dot++) {
      addSpecialtyEllipsePair(parent, currentRow, x + textWidth + (dot * (dotsSize + 1)) + spacing, height
          - dotsSize
          + y, dataSourceName, dot);
    }
    return height;
  }

  public int encodeBackgroundRow(Element parent, int currentRow, int x, int y, int width, int dotCount, int spacing) {
    return encodeNamedTraitDataSourceRow(
        parent,
        currentRow,
        x,
        y,
        width,
        dotCount,
        spacing,
        ICharacterReportConstants.BACKGROUND_DATA_SOURCE);
  }

  public int getBackgroundValueWidth(int dotCount) {
    return (dotsSize + 1) * dotCount;
  }

  private void addLineOrText(Element parent, int x, int y, int width, String printWhenExpression, String text) {
    Element textReportElement = addTextElement(parent, text, fontSize, "Left", x, y, width, height); //$NON-NLS-1$
    textReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression);
    Element lineReportElement = addThinLineElement(parent, new Rectangle(x, y + height, width, 0));
    lineReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA("!(" + printWhenExpression + ")"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public int encodeBackgroundRowWithDescription(
      Element parent,
      int currentRow,
      int x,
      int y,
      int dotCount,
      int spacing,
      int backgroundWidth,
      int descriptionWidth) {
    encodeBackgroundRow(parent, currentRow, x, y, backgroundWidth, dotCount, spacing);
    String dataSource = ParameterUtilities.parameterString(ICharacterReportConstants.BACKGROUND_DATA_SOURCE);
    String description = dataSource
        + methodCall("getValue", new Object[] { currentRow, quotify(BackgroundsDataSource.COLUMN_DESCRIPTION) }); //$NON-NLS-1$
    String printWhenExpression = description + " !=  null && ((java.lang.String)" + description + ") .length() > 0";
    addLineOrText(parent, x + backgroundWidth + spacing, y, descriptionWidth, printWhenExpression, description);
    return height;
  }

  public void addEllipsePair(Element parent, int x, int y, String parameterName, int value, int size) {
    String parameterValueString = "$P{" + parameterName + "}.intValue()"; //$NON-NLS-1$ //$NON-NLS-2$
    addShape(parent, TAG_ELLIPSE, VALUE_COLOR_BLACK, x, y, size, parameterValueString + ">" + value); //$NON-NLS-1$ //$NON-NLS-2$
    addShape(parent, TAG_ELLIPSE, VALUE_COLOR_WHITE, x, y, size, parameterValueString + "<=" + value); //$NON-NLS-1$ //$NON-NLS-2$
  }

  private void addSpecialtyEllipsePair(Element parent, int currentRow, int x, int y, String sourceName, int value) {
    String parameterValueString = "((Integer)" //$NON-NLS-1$
        + ParameterUtilities.parameterString(sourceName)
        + methodCall("getValue", new Object[] { currentRow, quotify(SpecialtiesDataSource.COLUMN_VALUE) }) + ")" + methodCall("intValue"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    addShape(parent, TAG_ELLIPSE, VALUE_COLOR_BLACK, x, y, dotsSize, parameterValueString + ">" + value); //$NON-NLS-1$ //$NON-NLS-2$
    addShape(parent, TAG_ELLIPSE, VALUE_COLOR_WHITE, x, y, dotsSize, parameterValueString + "<=" + value); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public void addRectanglePair(Element parent, int x, int y, String parameterName) {
    String parameterValueString = "$P{" + parameterName + "}.booleanValue()"; //$NON-NLS-1$ //$NON-NLS-2$
    addShape(parent, TAG_RECTANGLE, VALUE_COLOR_BLACK, x, y, dotsSize, parameterValueString); //$NON-NLS-1$ //$NON-NLS-2$
    addShape(parent, TAG_RECTANGLE, VALUE_COLOR_WHITE, x, y, dotsSize, "!" + parameterValueString); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public int getLineHeight() {
    return height;
  }
}