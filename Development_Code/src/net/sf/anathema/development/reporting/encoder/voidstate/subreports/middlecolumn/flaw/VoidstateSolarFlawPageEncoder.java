package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.flaw;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;

import org.dom4j.Element;

public class VoidstateSolarFlawPageEncoder extends AbstractVoidstateFlawPageEncoder implements IVoidStateFormatConstants {

  private final IOneColumnEncoder columnEncoder;

  public VoidstateSolarFlawPageEncoder(IOneColumnEncoder basicsEncoder) {
    this.columnEncoder = basicsEncoder;
  }

  public int encodeBand(Element bandElement) {
    Rectangle boxRectangle = calculateBounds(columnEncoder);
    Rectangle textRect = columnEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Limit Break");
    int yOffset = addShapeRow(bandElement, TAG_RECTANGLE, textRect.y + 3, textRect.x, 10, textRect.width, 4);
    yOffset += 6;
    yOffset = addVirtueFlawPart(bandElement, textRect, yOffset);
    return boxRectangle.height;
  }

  private int addVirtueFlawPart(Element bandElement, Rectangle textRect, int yOffset) {
    columnEncoder.addCentralHeader(bandElement, textRect, textRect.y + yOffset, "Virtue Flaw");
    yOffset += 8;
    columnEncoder.addParameterOrLine(bandElement, ExaltVoidstateReportTemplate.VIRTUE_FLAW, textRect.x, textRect.y
        + yOffset, textRect.width - 1);
    yOffset += LINE_HEIGHT;
    addTextElement(
        bandElement,
        quotify("Limit Break Condition: ") + "+" + ParameterUtilities.parameterString(ExaltVoidstateReportTemplate.VIRTUE_FLAW_CONDITION),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x,
        textRect.y + yOffset,
        textRect.width - 5,
        LINE_HEIGHT);
    Element firstLineReportElement = addThinLineElement(bandElement, new Rectangle(textRect.x + 70, textRect.y
        + yOffset
        + LINE_HEIGHT, textRect.width - 75, 0));
    String parameterString = ParameterUtilities.parameterString(ExaltVoidstateReportTemplate.VIRTUE_FLAW_CONDITION);
    String printWhenExpression = parameterString + "==null||" + parameterString + methodCall("length") + "==0"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    firstLineReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression); //$NON-NLS-1$ //$NON-NLS-2$
    yOffset += LINE_HEIGHT;
    for (int index = 0; index < 2; index++) {
      Element lineReportElement = addThinLineElement(bandElement, new Rectangle(textRect.x, textRect.y
          + yOffset
          + (index + 1)
          * LINE_HEIGHT, textRect.width - 5, 0));
      lineReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return yOffset + 2 * LINE_HEIGHT;
  }

  public String getGroupName() {
    return "VoidStateSolarVirtueFlaw";
  }
}