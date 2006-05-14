package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.flaw.AbstractVoidstateFlawPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.virtues.VoidstateVirtuesPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.willpower.VoidstateDefaultWillpowerPageEncoder;

import org.dom4j.Element;

public class DefaultMiddleColumnPageEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  private final VoidstateBasicsEncoder basicsEncoder;

  public DefaultMiddleColumnPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    this.basicsEncoder = basicsEncoder;
  }

  public int encodeBand(Element bandElement) {
    int height = 0;
    height += encodeWillpower(height, bandElement);
    height += PADDING;
    height += encodeVirtues(height, bandElement);
    height += PADDING;
    height += encodeFlaw(height, bandElement);
    return height;
  }

  private int encodeFlaw(int y, Element bandElement) {
    Rectangle boxRectangle = basicsEncoder.createOneColumnBoxBoundsWithTitle(0, 10, new Point(0, y));
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_FLAW_SUBREPORT;
    encodeSubreport(bandElement, boxRectangle, subreportParameterName);
    return boxRectangle.height;
  }

  private int encodeVirtues(int y, Element bandElement) {
    Rectangle boxRectangle = basicsEncoder.createOneColumnBoxBoundsWithTitle(3, 1, new Point(0, y));
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_VIRTUES_SUBREPORT;
    encodeSubreport(bandElement, boxRectangle, subreportParameterName);
    return boxRectangle.height;
  }

  private int encodeWillpower(int y, Element bandElement) {
    Rectangle boxRectangle = basicsEncoder.createOneColumnBoxBoundsWithTitle(5, 1, new Point(0, y));
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_WILLPOWER_SUBREPORT;
    encodeSubreport(bandElement, boxRectangle, subreportParameterName);
    return boxRectangle.height;
  }

  public String getGroupName() {
    return "DefaultMiddleColumnSubreport";
  }

  public static Rectangle getExtents(VoidstateBasicsEncoder basicsEncoder) {
    int width = basicsEncoder.getSingleColumnWidth();
    int height = 0;
    height += VoidstateDefaultWillpowerPageEncoder.getExtents(basicsEncoder).height;
    height += VoidstateVirtuesPageEncoder.getExtents(basicsEncoder).height;
    height += AbstractVoidstateFlawPageEncoder.calculateBounds(basicsEncoder).height;
    height += 2 * PADDING;
    return new Rectangle(width, height);
  }
}
