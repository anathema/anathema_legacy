package net.sf.anathema.development.reporting.encoder.voidstate.format;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.util.TextLineEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.VoidStateBoxEncoder;
import net.sf.anathema.development.reporting.util.AbstractJasperEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;

import org.dom4j.Element;

public class VoidstateBasicsEncoder extends AbstractJasperEncoder implements
    IVoidStateFormatConstants,
    IOneColumnEncoder {

  private final VoidStateBoxEncoder boxEncoder = new VoidStateBoxEncoder(TITLE_HEIGHT);
  private final TextLineEncoder lineEncoder = new TextLineEncoder(FONT_SIZE, LINE_HEIGHT);
  private final int oneColumnWidth;
  private final int threeColumnWidth;
  private final int twoColumnWidth;

  public VoidstateBasicsEncoder(int pageWidth) {
    oneColumnWidth = (pageWidth - 2 * PADDING) / 3;
    twoColumnWidth = (2 * oneColumnWidth) + PADDING;
    threeColumnWidth = pageWidth;
  }

  private Rectangle createBoxBounds(int lineCount, int paddingCount, Point position, int width, boolean withTitle) {
    int textHeight = (lineCount * LINE_HEIGHT) + paddingCount * TEXT_PADDING;
    if (withTitle) {
      textHeight += TITLE_HEIGHT;
    }
    return boxEncoder.calculateBounds(position.x, position.y, width, textHeight);
  }

  private Rectangle createCorrectedBoxBounds(
      int lineCount,
      int paddingCount,
      Point position,
      int width,
      boolean withTitle) {
    int textHeight = (lineCount * LINE_HEIGHT) + paddingCount * TEXT_PADDING;
    if (withTitle) {
      textHeight += TITLE_HEIGHT;
    }
    return boxEncoder.calculateCorrectedBounds(position.x, position.y, width, textHeight);
  }

  public Rectangle createOneColumnlBoxBoundsWithoutTitle(int lineCount, int paddingCount, Point position) {
    return createBoxBounds(lineCount, paddingCount, position, oneColumnWidth, false);
  }

  public Rectangle createOneColumnBoxBoundsWithTitle(int lineCount, int paddingCount, Point position) {
    return createBoxBounds(lineCount, paddingCount, position, oneColumnWidth, true);
  }

  public Rectangle createCorrectedOneColumnBoxBoundsWithTitle(int lineCount, int paddingCount, Point position) {
    return createCorrectedBoxBounds(lineCount, paddingCount, position, oneColumnWidth, true);
  }

  public Rectangle createCorrectedOneColumnBoxBoundsWithoutTitle(int lineCount, int paddingCount, Point position) {
    return createCorrectedBoxBounds(lineCount, paddingCount, position, oneColumnWidth, false);
  }

  public final Rectangle createThreeColumnBoxBoundsWithTitle(int lineCount, Point position) {
    return createBoxBounds(lineCount, 0, position, threeColumnWidth, true);
  }

  public final Rectangle createTwoColumnBoxBoundsWithTitle(int lineCount, Point position) {
    return createBoxBounds(lineCount, 0, position, twoColumnWidth, true);
  }

  public final Rectangle createCorrectedTwoColumnBoxBoundsWithTitle(int lineCount, Point position) {
    return createCorrectedBoxBounds(lineCount, 0, position, twoColumnWidth, true);
  }

  public final Rectangle createCorrectedTwoColumnBoxBoundsWithoutTitle(int lineCount, Point position) {
    return createCorrectedBoxBounds(lineCount, 0, position, twoColumnWidth, false);
  }

  public Rectangle createTwoColumnBoxBoundsWithoutTitle(int lineCount, int paddingCount, Point position) {
    return createBoxBounds(lineCount, paddingCount, position, twoColumnWidth, false);
  }

  public final void addEmptyLines(Element parent, Rectangle rect, int lineCount) {
    addEmptyLines(parent, rect.getLocation(), rect.width, lineCount);
  }

  public final void addSometimesPrintedEmptyLines(
      Element parent,
      Rectangle rect,
      int lineCount,
      String printWhenExpression) {
    addSometimesPrintedEmptyLines(parent, rect.getLocation(), rect.width, lineCount, printWhenExpression);
  }

  public final void addEmptyLines(Element parent, Point position, int width, int lineCount) {
    for (int index = 0; index < lineCount; index++) {
      addThinLineElement(parent, new Rectangle(position.x, position.y + (1 + index) * LINE_HEIGHT, width, 0));
    }
  }

  public final void addSometimesPrintedEmptyLines(
      Element parent,
      Point position,
      int width,
      int lineCount,
      String printWhenExpresseion) {
    for (int index = 0; index < lineCount; index++) {
      Element reportElement = addThinLineElement(parent, new Rectangle(position.x, position.y
          + (1 + index)
          * LINE_HEIGHT, width, 0));
      addPrintWhenExpression(reportElement, printWhenExpresseion);
    }
  }

  public int getFirstColumnX() {
    return 0;
  }

  public int getSecondColumnX() {
    return oneColumnWidth + PADDING;
  }

  public int getThirdColumnX() {
    return threeColumnWidth - oneColumnWidth;
  }

  public Rectangle encodeBoxAndQuotifyHeader(Element bandElement, Rectangle boxRectangle, String header) {    
    return boxEncoder.encodeWithTitle(bandElement, boxRectangle, quotify(header));
  }
  
  public Rectangle encodeBoxWithoutHeader(Element bandElement, Rectangle boxRectangle) {
    return boxEncoder.encodeWithoutTitle(bandElement, boxRectangle);
  }

  public Rectangle encodeBox(Element bandElement, Rectangle boxRectangle, String header) {
    return boxEncoder.encodeWithTitle(bandElement, boxRectangle, header);
  }

  public Rectangle createOneColumnBox(Point location, int height) {
    return new Rectangle(location, new Dimension(oneColumnWidth, height));
  }

  public TextLineEncoder getTextLineEncoder() {
    return lineEncoder;
  }

  public final int addCentralHeader(Element parent, Rectangle box, int y, String header) {
    return addCentralHeader(parent, new Point(box.x, y), box.width, header);
  }

  public final int addCentralHeader(Element parent, Point position, int width, String header) {
    addTextElement(parent, quotify(header), HEADER_FONT_SIZE, VALUE_CENTER, position.x, position.y, width, LINE_HEIGHT);
    return LINE_HEIGHT;
  }

  public final void addTextOrLine(
      Element parent,
      String textExpression,
      String printWhenExpression,
      int x,
      int y,
      int width) {
    addTextOrLine(parent, textExpression, printWhenExpression, negate(printWhenExpression), x, y, width);
  }

  public final void addTextOrLine(
      Element parent,
      String textExpression,
      String textPrintWhenExpression,
      String linePrintWhenExpression,
      int x,
      int y,
      int width) {
    addOptionalText(parent, textExpression, textPrintWhenExpression, x, y, width);
    Rectangle lineBounds = new Rectangle(x, y + LINE_HEIGHT, width - 4, 0);
    Element lineReportElement = addThinLineElement(parent, lineBounds);
    addPrintWhenExpression(lineReportElement, linePrintWhenExpression);
  }

  public final void addOptionalText(
      Element parent,
      String textExpression,
      String printWhenExpression,
      int x,
      int y,
      int width) {
    Rectangle textBounds = new Rectangle(x, y, width, LINE_HEIGHT);
    Element textReportElement = addTextElement(parent, textExpression, FONT_SIZE, VALUE_LEFT, textBounds);
    addPrintWhenExpression(textReportElement, printWhenExpression);
  }

  public final void addParameterOrLine(Element parent, String parameter, int x, int y, int width) {
    String textExpression = ParameterUtilities.parameterString(parameter) + methodCall("toString");
    String textPrintWhenExpression = negate(encodeIsStringParameterNullOrEmpty(parameter));
    addTextOrLine(parent, textExpression, textPrintWhenExpression, x, y, width);
  }

  public int getSingleColumnWidth() {
    return oneColumnWidth;
  }
}