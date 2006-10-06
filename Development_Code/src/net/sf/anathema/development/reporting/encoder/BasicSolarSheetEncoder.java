package net.sf.anathema.development.reporting.encoder;

import java.awt.Rectangle;
import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.development.reporting.util.HeaderData;
import net.sf.anathema.development.reporting.util.ParameterUtilities;
import net.sf.anathema.development.reporting.util.ReportDataSourceEncoder;
import net.sf.anathema.initialization.InitializationException;

import org.dom4j.Element;

public class BasicSolarSheetEncoder extends AbstractBasicCharacterSheetEncoder {

  public BasicSolarSheetEncoder() throws InitializationException {
    super();
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClassesByName) {
    // todo vom (08.09.2005) (sieroux): Reaktiviertung White Wolf SolarCharacter Sheet
    //AbstractExaltCharacterSheetReport.addExaltParameterClasses(parameterClassesByName);
  }

  @Override
  protected final String getReportName() {
    return "BasicSolarCharacterSheet"; //$NON-NLS-1$
  }

  @Override
  protected final int getSpecialtyLineCount() {
    return 5;
  }

  @Override
  protected ReportDataSourceEncoder createAdvantageDataSourceEncoder() {
    return ReportDataSourceEncoder.createCharmEncoder(LINE_HEIGHT);
  }

  @Override
  protected HeaderData[] getHeaderData() {
    return new HeaderData[] {
        new HeaderData("Name", ICharacterReportConstants.CHARACTER_NAME),
        new HeaderData("Concept", ICharacterReportConstants.CONCEPT),
        new HeaderData("Caste", "caste"),
        new HeaderData("Anima"),
        new HeaderData("Nature", ICharacterReportConstants.NATURE),
        new HeaderData("Player") };
  }

  private int encodeLimitBreak(Element parent, int y, int x, int width, int lineSpacing) {
    int originalY = y;
    addLinedHeader(parent, "Limit Break", y, x, width, lineSpacing + 5, lineSpacing + 5);
    y += HEADER_SIZE + LINE_HEIGHT / 2;
    y += addShapeRow(parent, TAG_RECTANGLE, y, x, 10, width, 4);
    y += LINE_HEIGHT / 2;
    return y - originalY;
  }

  @Override
  protected final int encodeLeftRestColumn(Element bandElement, int y, int x, int spacing, int restColumnWidth) {
    int yOffsetLeft = 0;
    yOffsetLeft += encodeTitledEmptyLines(bandElement, "Weapons", 4, y, x, restColumnWidth, spacing);
    yOffsetLeft += encodeTitledEmptyLines(bandElement, "Anima", 4, y + yOffsetLeft, x, restColumnWidth, spacing);
    yOffsetLeft += encodeLimitBreak(bandElement, y + yOffsetLeft, x, restColumnWidth, spacing);
    yOffsetLeft += encodeVirtueFlaw(bandElement, y + yOffsetLeft, x, restColumnWidth, spacing);
    return yOffsetLeft;
  }

  private int encodeVirtueFlaw(Element parent, int y, int x, int width, int lineSpacing) {
    int originalY = y;
    addLinedHeader(parent, "Virtue Flaw", y, x, width, lineSpacing + 5, lineSpacing + 5);
    y += HEADER_SIZE;
    String virtueFlaw = ParameterUtilities.parameterString(ExaltVoidstateReportTemplate.VIRTUE_FLAW);
    addTextElement(parent, virtueFlaw, 8, VALUE_LEFT, x, y, width, 8);
    String printWhenExpression = virtueFlaw + "==null||" + virtueFlaw + methodCall("length") + "==0"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    Element flawFirstLineReportElement = addThinLineElement(parent, new Rectangle(x, y + LINE_HEIGHT, width, 0));
    flawFirstLineReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression); //$NON-NLS-1$ //$NON-NLS-2$
    y += LINE_HEIGHT;
    Element flawSecondLineReportElement = addThinLineElement(parent, new Rectangle(x, y + LINE_HEIGHT, width, 0));
    flawSecondLineReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression); //$NON-NLS-1$ //$NON-NLS-2$
    y += LINE_HEIGHT;
    return y - originalY;
  }

  @Override
  protected CharacterType getCharacterType() {
    return CharacterType.SOLAR;
  }

  @Override
  protected String getAdvantageDataSourceTitle() {
    return "Charms / Spells";
  }
}