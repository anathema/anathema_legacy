package net.sf.anathema.development.reporting.encoder.voidstate.subreports.charmpage;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.datasource.CharmDataSource;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.AbstractReportEncoder;
import net.sf.anathema.development.reporting.ReportBuilder;
import net.sf.anathema.development.reporting.encoder.page.PageFormat;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.util.AbstractJasperEncoder;
import net.sf.anathema.development.reporting.util.DataSourceColumn;
import net.sf.anathema.development.reporting.util.VoidstateDataSourceUtils;
import net.sf.anathema.framework.reporting.jasper.IJasperXmlConstants;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class VoidstateCharmPageSubreportEncoder extends AbstractReportEncoder {

  private static final String TAG_FIELD = "field"; //$NON-NLS-1$
  private static final String TAG_DETAIL = "detail";
  private final VoidstateBasicsEncoder basicsEncoder;

  public VoidstateCharmPageSubreportEncoder() {
    super(PageFormat.recreateWithoutInsets(ReportBuilder.VOID_STATE_PAGE_FORMAT));
    this.basicsEncoder = new VoidstateBasicsEncoder(getPageFormat().getColumnWidth());
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    // Nothing to do

  }

  @Override
  protected void encodeContent(Element rootElement) {
    addFieldElements(rootElement);
    Rectangle rectangle = addPageHeader(rootElement);
    addDetails(rootElement, rectangle);
  }

  private void addDetails(Element rootElement, Rectangle rectangle) {
    Element detailElement = rootElement.addElement(TAG_DETAIL);
    Element bandElement = addBandElement(detailElement, IVoidStateFormatConstants.LINE_HEIGHT);
    DataSourceColumn[] magicDataSourceColumns = VoidstateDataSourceUtils.getMagicDataSourceColumns();
    int textWidth = VoidstateDataSourceUtils.calculatePrintWidth(
        rectangle.width,
        IVoidStateFormatConstants.TEXT_PADDING,
        5);
    int columnX = rectangle.x;
    for (DataSourceColumn column : magicDataSourceColumns) {
      int columnWidth = (int) (textWidth * column.getWidthShare());
      addTextElement(
          bandElement,
          "$F{" + column.getColumnName() + "}",
          IVoidStateFormatConstants.FONT_SIZE - 1,
          VALUE_LEFT,
          new Rectangle(columnX, 0, columnWidth, IVoidStateFormatConstants.LINE_HEIGHT));
      columnX += columnWidth + IVoidStateFormatConstants.TEXT_PADDING;
    }
  }

  private Rectangle addPageHeader(Element rootElement) {
    Element headerElement = rootElement.addElement("pageHeader");
    Element bandElement = addBandElement(headerElement, 25);
    Rectangle boxRectangle = basicsEncoder.createThreeColumnBoxBoundsWithTitle(2, new Point(0, 0));
    Rectangle textRect = basicsEncoder.encodeBox(bandElement, boxRectangle, quotify("Charms & Sorcery ")
        + "+String.valueOf($V{PAGE_NUMBER} + 1 )");
    DataSourceColumn[] magicDataSourceColumns = VoidstateDataSourceUtils.getMagicDataSourceColumns();
    encodeVoidstateHeader(
        bandElement,
        textRect.y,
        textRect.x,
        textRect.width,
        IVoidStateFormatConstants.TEXT_PADDING,
        IVoidStateFormatConstants.FONT_SIZE,
        magicDataSourceColumns,
        IVoidStateFormatConstants.LINE_HEIGHT);
    return textRect;
  }

  private Element addBandElement(Element headerElement, int height) {
    Element bandElement = headerElement.addElement("band");
    ElementUtilities.addAttribute(bandElement, ATTRIB_HEIGHT, height);
    return bandElement;
  }

  private void addFieldElements(Element rootElement) {
    addFieldElement(rootElement, CharmDataSource.COLUMN_PRINT_NAME);
    addFieldElement(rootElement, CharmDataSource.COLUMN_COST);
    addFieldElement(rootElement, CharmDataSource.COLUMN_DURATION);
    addFieldElement(rootElement, CharmDataSource.COLUMN_TYPE);
    addFieldElement(rootElement, CharmDataSource.COLUMN_SOURCE);
  }

  private void addFieldElement(Element rootElement, String fieldName) {
    Element fieldElement = rootElement.addElement(TAG_FIELD);
    fieldElement.addAttribute(ATTRIB_NAME, fieldName);
    fieldElement.addAttribute(IJasperXmlConstants.ATTRIB_CLASS, String.class.getName());
  }

  @Override
  protected String getReportName() {
    return "VoidstateCharmPageSubreport";
  }

  private int encodeVoidstateHeader(
      Element parent,
      int y,
      int x,
      int width,
      int spacing,
      int fontSize,
      DataSourceColumn[] headerColumns,
      int headerLineHeight) {
    int cumulatedLineWidth = VoidstateDataSourceUtils.calculatePrintWidth(width, spacing, headerColumns.length);
    int columnX = x;
    for (int columnIndex = 0; columnIndex < headerColumns.length; columnIndex++) {
      DataSourceColumn column = headerColumns[columnIndex];
      int columnWidth = (int) (cumulatedLineWidth * column.getWidthShare());
      String content = AbstractJasperEncoder.quotify(column.getHeader());
      addTextElement(
          parent,
          content,
          columnIndex == 0 ? IVoidStateFormatConstants.HEADER_FONT_SIZE : fontSize,
          "Left", columnX, y, columnWidth, headerLineHeight); //$NON-NLS-1$
      columnX += columnWidth + spacing;
    }
    return headerLineHeight;
  }
}