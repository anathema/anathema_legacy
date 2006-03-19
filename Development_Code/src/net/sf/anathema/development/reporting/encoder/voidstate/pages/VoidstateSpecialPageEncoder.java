package net.sf.anathema.development.reporting.encoder.voidstate.pages;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.development.reporting.ReportBuilder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;

import org.dom4j.Element;

public class VoidstateSpecialPageEncoder extends AbstractVoidstatePageEncoder {

  public static Rectangle getSpecialPageExtents() {
    int subReportWidth = ReportBuilder.VOID_STATE_PAGE_FORMAT.getPageSize().width
        - ReportBuilder.VOID_STATE_PAGE_FORMAT.getInsets().left
        - ReportBuilder.VOID_STATE_PAGE_FORMAT.getInsets().right;
    int subreportHeight = ReportBuilder.VOID_STATE_PAGE_FORMAT.getPageSize().height
        - ReportBuilder.VOID_STATE_PAGE_FORMAT.getInsets().top
        - ReportBuilder.VOID_STATE_PAGE_FORMAT.getInsets().bottom;
    return new Rectangle(subReportWidth, subreportHeight);
  }

  public VoidstateSpecialPageEncoder(int pageWidth) {
    super(pageWidth);
  }

  public int encodeBand(Element bandElement) {
    Rectangle specialPageExtents = getSpecialPageExtents();
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_SPECIALPAGE_SUBREPORT;
    encodeSubreport(bandElement, specialPageExtents, subreportParameterName);
    return specialPageExtents.height;
  }

  public String getGroupName() {
    return "VoidstateSpecialPage";
  }

  @Override
  public String getPrintWhenExpression() {
    return ParameterUtilities.parameterString(ICharacterReportConstants.PRINT_SPECIAL_PAGE) + "==" + Boolean.TRUE;
  }
}