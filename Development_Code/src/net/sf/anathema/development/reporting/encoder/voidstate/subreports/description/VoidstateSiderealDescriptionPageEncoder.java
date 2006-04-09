package net.sf.anathema.development.reporting.encoder.voidstate.subreports.description;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.sidereal.reporting.ResplendentDestinyDataSource;
import net.sf.anathema.development.reporting.encoder.sidereal.NamedTraitEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.pages.VoidstateSecondPageEncoder;
import net.sf.anathema.development.reporting.util.DataSourceColumn;
import net.sf.anathema.development.reporting.util.ReportDataSourceEncoder;
import net.sf.anathema.development.reporting.util.TraitEncoder;
import net.sf.anathema.framework.reporting.encoding.IJasperXmlConstants;

import org.dom4j.Element;

public class VoidstateSiderealDescriptionPageEncoder extends AbstractVoidstateDualBoxDescriptionPageEncoder {
  private final TraitEncoder traitEncoder;

  public VoidstateSiderealDescriptionPageEncoder(VoidstateBasicsEncoder basicsEncoder, TraitEncoder traitEncoder) {
    super(basicsEncoder);
    this.traitEncoder = traitEncoder;
  }

  @Override
  protected void encodeSecondBox(Element bandElement, Rectangle secondBoxBounds) {
    encodeColleges(bandElement, secondBoxBounds);
  }

  @Override
  protected void encodeFirstBox(Element bandElement, Rectangle firstBoxBounds) {
    encodeResplendentDestinies(bandElement, firstBoxBounds);
  }

  private void encodeColleges(Element bandElement, Rectangle bounds) {
    Rectangle textRect = getBasicsEncoder().encodeBoxAndQuotifyHeader(bandElement, bounds, "Colleges");
    NamedTraitEncoder collegeEncoder = new NamedTraitEncoder(
        traitEncoder,
        ExaltVoidstateReportTemplate.COLLEGES_DATA_SOURCE,
        "College");
    collegeEncoder.encodeTraitDataSourceRows(
        bandElement,
        textRect.y,
        textRect.x,
        textRect.width,
        VoidstateSecondPageEncoder.LOWEST_LINE_COUNT - 1);
  }

  private void encodeResplendentDestinies(Element bandElement, Rectangle bounds) {
    Rectangle textRect = getBasicsEncoder().encodeBoxAndQuotifyHeader(bandElement, bounds, "Resplendent Destinies");
    ReportDataSourceEncoder destinyEencoder = createResplendentDestinyEncoder(LINE_HEIGHT);
    destinyEencoder.encodeWithVoidstateHeader(
        bandElement,
        textRect.y,
        textRect.x,
        textRect.width,
        TEXT_PADDING,
        VoidstateSecondPageEncoder.LOWEST_LINE_COUNT - 1,
        FONT_SIZE);
  }

  private static ReportDataSourceEncoder createResplendentDestinyEncoder(int lineHeight) {
    return new ReportDataSourceEncoder(
        lineHeight,
        ExaltVoidstateReportTemplate.RESPLENDENT_DESTINY_DATA_SOURCE,
        new DataSourceColumn[] {
            new DataSourceColumn(
                ResplendentDestinyDataSource.COLUMN_PRINT_NAME,
                0.50,
                "Name",
                IJasperXmlConstants.VALUE_LEFT),
            new DataSourceColumn(
                ResplendentDestinyDataSource.COLUMN_COLLEGE,
                0.15,
                "College",
                IJasperXmlConstants.VALUE_LEFT),
            new DataSourceColumn(
                ResplendentDestinyDataSource.COLUMN_EFFECT,
                0.15,
                "Effect",
                IJasperXmlConstants.VALUE_LEFT),
            new DataSourceColumn(
                ResplendentDestinyDataSource.COLUMN_DURATION,
                0.20,
                "Duration",
                IJasperXmlConstants.VALUE_LEFT) });
  }

  public String getGroupName() {
    return "VoidstateSiderealDescriptionSubreport";
  }
}