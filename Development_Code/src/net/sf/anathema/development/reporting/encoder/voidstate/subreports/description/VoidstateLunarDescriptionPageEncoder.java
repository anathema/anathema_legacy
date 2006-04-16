package net.sf.anathema.development.reporting.encoder.voidstate.subreports.description;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.lunar.renown.model.RenownType;
import net.sf.anathema.character.lunar.reporting.HeartsBloodDataSource;
import net.sf.anathema.character.lunar.reporting.RenownDataSource;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.pages.VoidstateSecondPageEncoder;
import net.sf.anathema.development.reporting.util.DataSourceColumn;
import net.sf.anathema.development.reporting.util.ParameterUtilities;
import net.sf.anathema.development.reporting.util.ReportDataSourceEncoder;
import net.sf.anathema.framework.reporting.jasper.IJasperXmlConstants;

import org.dom4j.Element;

public class VoidstateLunarDescriptionPageEncoder extends AbstractVoidstateDualBoxDescriptionPageEncoder {

  public VoidstateLunarDescriptionPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    super(basicsEncoder);
  }

  @Override
  protected void encodeFirstBox(Element bandElement, Rectangle firstBoxBounds) {
    Rectangle textRect = getBasicsEncoder().encodeBoxAndQuotifyHeader(bandElement, firstBoxBounds, "Animal Forms");
    ReportDataSourceEncoder heartsBloodEncoder = createHeartsBloodEncoder(LINE_HEIGHT);
    heartsBloodEncoder.encodeWithVoidstateHeader(
        bandElement,
        textRect.y,
        textRect.x,
        textRect.width,
        TEXT_PADDING,
        VoidstateSecondPageEncoder.LOWEST_LINE_COUNT - 1,
        FONT_SIZE);
  }

  private ReportDataSourceEncoder createHeartsBloodEncoder(int lineHeight) {
    return new ReportDataSourceEncoder(
        lineHeight,
        ExaltVoidstateReportTemplate.HEARTS_BLOOD_DATA_SOURCE,
        new DataSourceColumn[] {
            new DataSourceColumn(HeartsBloodDataSource.COLUMN_PRINT_NAME, 0.70, "Name"),
            new DataSourceColumn(
                HeartsBloodDataSource.COLUMN_STRENGTH,
                0.15,
                "Strength",
                IJasperXmlConstants.VALUE_CENTER),
            new DataSourceColumn(
                HeartsBloodDataSource.COLUMN_STAMINA,
                0.15,
                "Stamina",
                IJasperXmlConstants.VALUE_CENTER) });
  }

  @Override
  protected void encodeSecondBox(Element bandElement, Rectangle secondBoxBounds) {
    Rectangle textRect = getBasicsEncoder().encodeBoxAndQuotifyHeader(bandElement, secondBoxBounds, "Face");
    ReportDataSourceEncoder renownEncoder = createRenownEncoder(LINE_HEIGHT);
    int lines = RenownType.values().length;
    renownEncoder.encode(bandElement, textRect.y, textRect.x, textRect.width, TEXT_PADDING, lines);
    addThinLineElement(bandElement, new Rectangle(textRect.x, textRect.y + lines * LINE_HEIGHT, textRect.width, 0));
    addTextElement(bandElement, quotify("Renown"), FONT_SIZE, VALUE_LEFT, new Rectangle(textRect.x, textRect.y
        + (lines)
        * LINE_HEIGHT, textRect.width, LINE_HEIGHT));
    addTextElement(
        bandElement,
        ParameterUtilities.parameterString(ExaltVoidstateReportTemplate.PARAM_RENOWN),
        FONT_SIZE,
        VALUE_RIGHT,
        new Rectangle(
            (int) (textRect.x + textRect.width * 0.7),
            textRect.y + (lines) * LINE_HEIGHT,
            (int) (textRect.width * 0.3),
            LINE_HEIGHT));
    addTextElement(bandElement, quotify("Face"), FONT_SIZE, VALUE_LEFT, new Rectangle(textRect.x, textRect.y
        + (lines + 2)
        * LINE_HEIGHT, textRect.width, LINE_HEIGHT));
    addTextElement(
        bandElement,
        ParameterUtilities.parameterString(ExaltVoidstateReportTemplate.PARAM_FACE),
        FONT_SIZE,
        VALUE_RIGHT,
        new Rectangle(textRect.x, textRect.y + (lines + 2) * LINE_HEIGHT, textRect.width, LINE_HEIGHT));
  }

  private ReportDataSourceEncoder createRenownEncoder(int lineheight) {
    return new ReportDataSourceEncoder(
        lineheight,
        ExaltVoidstateReportTemplate.RENOWN_DATA_SOURCE,
        new DataSourceColumn[] {
            new DataSourceColumn(RenownDataSource.COLUMN_PRINT_NAME, 0.70, "Name"),
            new DataSourceColumn(RenownDataSource.COLUMN_VALUE, 0.30, "Value", IJasperXmlConstants.VALUE_RIGHT) });
  }

  public String getGroupName() {
    return "VoidstateLunarDescriptionSubreport";
  }
}