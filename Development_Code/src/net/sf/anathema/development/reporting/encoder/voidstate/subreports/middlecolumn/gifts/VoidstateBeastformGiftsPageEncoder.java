package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.gifts;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.lunar.reporting.BeastformGiftDatasource;
import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.development.reporting.util.DataSourceColumn;
import net.sf.anathema.development.reporting.util.ReportDataSourceEncoder;

import org.dom4j.Element;

public class VoidstateBeastformGiftsPageEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  private final IOneColumnEncoder columnEncoder;

  public VoidstateBeastformGiftsPageEncoder(IOneColumnEncoder columnEncoder) {
    this.columnEncoder = columnEncoder;
  }

  public int encodeBand(Element bandElement) {
    //    ITextFormat smallTextFormat = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1);
    Rectangle boxRectangle = getExtents(columnEncoder);
    Rectangle textRect = columnEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Gifts");
    DataSourceColumn[] columns = new DataSourceColumn[] { new DataSourceColumn(
        BeastformGiftDatasource.COLUMN_NAME,
        1.0,
        null) };
    ReportDataSourceEncoder encoder = new ReportDataSourceEncoder(
        LINE_HEIGHT,
        ExaltVoidstateReportTemplate.PARAM_GIFT_DATA_SOURCE,
        columns);
    encoder.encodeHorizontallyRepeated(
        bandElement,
        textRect.y - 1,
        textRect.x,
        textRect.width,
        PADDING,
        2,
        5,
        FONT_SIZE - 1);
    return boxRectangle.height;
  }

  public String getGroupName() {
    return "VoidstateBeastformGiftsSubreport";
  }

  public static Rectangle getExtents(IOneColumnEncoder columnEncoder) {
    Rectangle boxRectangle = columnEncoder.createOneColumnBoxBoundsWithTitle(0, 7, new Point(0, 0));
    return boxRectangle;
  }
}