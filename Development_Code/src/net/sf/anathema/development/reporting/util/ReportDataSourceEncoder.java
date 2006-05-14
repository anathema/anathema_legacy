package net.sf.anathema.development.reporting.util;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.datasource.CharmDataSource;
import net.sf.anathema.character.generic.framework.reporting.datasource.MeritsAndFlawsDataSource;
import net.sf.anathema.character.generic.framework.reporting.template.AbstractMagicUserCharacterReportTemplate;
import net.sf.anathema.character.generic.framework.reporting.template.AbstractStattedCharacterReportTemplate;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;

import org.dom4j.Element;

public class ReportDataSourceEncoder extends AbstractJasperEncoder {

  private final int lineHeight;
  private final String dataSourceParameterName;
  private final DataSourceColumn[] columns;

  public static ReportDataSourceEncoder createCharmEncoder(int lineHeight) {
    return new ReportDataSourceEncoder(
        lineHeight,
        AbstractMagicUserCharacterReportTemplate.CHARM_DATA_SOURCE,
        createEqualWidthColumns(CharmDataSource.COLUMN_PRINT_NAME, CharmDataSource.COLUMN_COST));
  }

  public static ReportDataSourceEncoder createMeritAndFlawEncoder(int lineHeight) {
    return new ReportDataSourceEncoder(
        lineHeight,
        AbstractStattedCharacterReportTemplate.MERIT_AND_FLAW_DATA_SOURCE,
        createEqualWidthColumns(MeritsAndFlawsDataSource.COLUMN_PRINT_NAME, MeritsAndFlawsDataSource.COLUMN_TEXT));
  }

  public static ReportDataSourceEncoder createVoidstateMagicEncoder(int lineHeight) {
    return new ReportDataSourceEncoder(
        lineHeight,
        AbstractMagicUserCharacterReportTemplate.CHARM_DATA_SOURCE,
        VoidstateDataSourceUtils.getMagicDataSourceColumns());
  }

  private static DataSourceColumn[] createEqualWidthColumns(String leftColumn, String rightColumn) {
    return new DataSourceColumn[] {
        new DataSourceColumn(leftColumn, 0.5, null, VALUE_LEFT),
        new DataSourceColumn(rightColumn, 0.5, null, VALUE_LEFT) };
  }

  public ReportDataSourceEncoder(int lineHeight, String dataSourceParameterName, DataSourceColumn[] columns) {
    this.lineHeight = lineHeight;
    this.dataSourceParameterName = dataSourceParameterName;
    this.columns = columns;
  }

  public int encode(Element parent, int y, int x, int width, int spacing, int rowCount) {
    return encode(parent, y, x, width, spacing, rowCount, getFontSize());
  }

  private int encode(Element parent, int y, int x, int width, int spacing, int rowCount, int fontSize) {
    int height = 0;
    for (int index = 0; index < rowCount; index++) {
      height += encodeRow(parent, index, x, y + (index * lineHeight), width, spacing, fontSize, columns); //$NON-NLS-1$
    }
    return height;
  }

  public int encodeHorizontallyRepeated(
      Element parent,
      int y,
      int x,
      int totalWidth,
      int spacing,
      int repetitions,
      int displayRowCount,
      int fontSize) {
    int height = 0;
    int widthPerRepetition = (totalWidth - (repetitions - 1) * spacing) / repetitions;
    for (int index = 0; index < displayRowCount * repetitions; index++) {

      int xCoordinate = x + (index % repetitions) * (widthPerRepetition + spacing);
      int yCoordinate = y + (index / repetitions) * lineHeight;
      height += encodeRow(parent, index, xCoordinate, yCoordinate, widthPerRepetition, spacing, fontSize, columns); //$NON-NLS-1$
    }
    return height;
  }

  public int encodeWithVoidstateHeader(Element parent, int y, int x, int width, int spacing, int rowCount, int fontSize) {
    int height = 0;
    height += encodeVoidstateHeader(parent, y, x, width, spacing, fontSize, columns, lineHeight);
    height += encode(parent, y + height, x, width, spacing, rowCount, fontSize - 1);
    return height;
  }

  private int getFontSize() {
    return lineHeight - 4;
  }

  private int encodeRow(
      Element parent,
      int currentRow,
      int x,
      int y,
      int width,
      int spacing,
      int fontSize,
      DataSourceColumn[] dataColumns) {
    int cumulatedLineWidth = VoidstateDataSourceUtils.calculatePrintWidth(width, spacing, dataColumns.length);
    String dataSource = ParameterUtilities.parameterString(dataSourceParameterName);
    String printWhenExpression = dataSource + methodCall("getRowCount") + " > " + currentRow; //$NON-NLS-1$ //$NON-NLS-2$
    int columnX = x;

    for (int columnIndex = 0; columnIndex < dataColumns.length; columnIndex++) {
      DataSourceColumn column = dataColumns[columnIndex];
      int columnWidth = (int) (cumulatedLineWidth * column.getWidthShare());
      String columnName = column.getColumnName();
      String content = dataSource + methodCall("getValue", new Object[] { currentRow, quotify(columnName) }); //$NON-NLS-1$
      Element contentReportElement = addTextElement(
          parent,
          content,
          fontSize,
          column.getJustification(),
          columnX,
          y,
          columnWidth,
          lineHeight);
      contentReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression);
      Element lineReportElement = addThinLineElement(parent, new Rectangle(columnX, y + lineHeight, columnWidth, 0));
      lineReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA("!(" + printWhenExpression + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      columnX += columnWidth + spacing;
    }
    return lineHeight;
  }

  public int encodeVoidstateHeader(
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