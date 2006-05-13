package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.attribute.VoidstateBeastformAttributePageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.gifts.VoidstateBeastformGiftsPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.virtues.VoidstateVirtuesPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.willpower.VoidstateBeastformWillpowerPageEncoder;

import org.dom4j.Element;

public class BeastformMiddleColumnPageEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  private final VoidstateBasicsEncoder basicsEncoder;

  public BeastformMiddleColumnPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    this.basicsEncoder = basicsEncoder;
  }

  public int encodeBand(Element bandElement) {
    int height = VoidstateBeastformAttributePageEncoder.getOverlapHeight(basicsEncoder);
    height += PADDING;
    height += encodeWillpower(height, bandElement);
    height += PADDING - 1;
    height += encodeVirtues(height, bandElement);
    height += PADDING - 1;
    height += encodeGifts(height, bandElement);
    return height;
  }

  private int encodeGifts(int y, Element bandElement) {
    Rectangle boxRectangle = VoidstateBeastformGiftsPageEncoder.getExtents(basicsEncoder);
    boxRectangle.y = y;
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_LUNAR_GIFTS_SUBREPORT;
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
    Rectangle boxRectangle = VoidstateBeastformWillpowerPageEncoder.getExtents(basicsEncoder);
    boxRectangle.y = y;
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_LUNAR_WILLPOWER_SUBREPORT;
    encodeSubreport(bandElement, boxRectangle, subreportParameterName);
    return boxRectangle.height;
  }

  public String getGroupName() {
    return "BeastformMiddleColumnSubreport";
  }

  public static Rectangle getExtents(VoidstateBasicsEncoder basicsEncoder) {
    int width = basicsEncoder.getSingleColumnWidth();
    int height = 3 * PADDING;
    height += VoidstateBeastformAttributePageEncoder.getOverlapHeight(basicsEncoder);
    height += VoidstateBeastformWillpowerPageEncoder.getExtents(basicsEncoder).height;
    height += VoidstateVirtuesPageEncoder.getExtents(basicsEncoder).height;
    height += VoidstateBeastformGiftsPageEncoder.getExtents(basicsEncoder).height;
    return new Rectangle(width, height);
  }
}