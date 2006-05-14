package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.willpower;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.util.WillpowerEncoder;

import org.dom4j.Element;

public class VoidstateBeastformWillpowerPageEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  private final VoidstateBasicsEncoder basicsEncoder;
  private final WillpowerEncoder willpowerEncoder = new WillpowerEncoder();

  public VoidstateBeastformWillpowerPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    this.basicsEncoder = basicsEncoder;
  }

  public static Rectangle getExtents(VoidstateBasicsEncoder basicsEncoder) {
    return basicsEncoder.createOneColumnBoxBoundsWithTitle(1, 1, new Point(0, 0));
  }

  public int encodeBand(Element bandElement) {
    Rectangle boxRectangle = getExtents(basicsEncoder);
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Willpower");
    int yOffset = willpowerEncoder.encodeWillpower(bandElement, textRect.y + 3, textRect.x, textRect.width, 4, 10);
    yOffset += LINE_HEIGHT;
    yOffset += addShapeRow(bandElement, TAG_RECTANGLE, textRect.y + yOffset, textRect.x, 10, textRect.width, 4);
    return boxRectangle.height;
  }

  public String getGroupName() {
    return "VoidstateBeastformWillpowerSubreport";
  }
}
