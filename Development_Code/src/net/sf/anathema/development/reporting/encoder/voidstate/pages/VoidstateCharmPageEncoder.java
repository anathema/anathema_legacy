package net.sf.anathema.development.reporting.encoder.voidstate.pages;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;

import org.dom4j.Element;

public class VoidstateCharmPageEncoder extends AbstractVoidstatePageEncoder {

  public VoidstateCharmPageEncoder(int pageWidth) {
    super(pageWidth);
  }

  public int encodeBand(Element bandElement) {
    Rectangle charmRect = new Rectangle(595, 767);
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_CHARMPAGE_SUBREPORT;
    encodeSubreportWithDatasource(
        bandElement,
        charmRect,
        subreportParameterName,
        ExaltVoidstateReportTemplate.CHARMPAGE_DATASOURCE);
    return 767;
  }

  public String getGroupName() {
    return "VoidstateCharmPage";
  }
}