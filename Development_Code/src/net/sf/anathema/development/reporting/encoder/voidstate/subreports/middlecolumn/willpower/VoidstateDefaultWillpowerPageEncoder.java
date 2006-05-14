package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.willpower;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.VoidStateNaturePartEncoder;
import net.sf.anathema.development.reporting.util.WillpowerEncoder;

import org.dom4j.Element;

public class VoidstateDefaultWillpowerPageEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  private final VoidstateBasicsEncoder basicsEncoder;
  private final VoidStateNaturePartEncoder naturePartEncoder;
  private final WillpowerEncoder willpowerEncoder = new WillpowerEncoder();

  public VoidstateDefaultWillpowerPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    this.basicsEncoder = basicsEncoder;
    this.naturePartEncoder = new VoidStateNaturePartEncoder(basicsEncoder);
  }

  public static Rectangle getExtents(VoidstateBasicsEncoder basicsEncoder) {
    return basicsEncoder.createOneColumnBoxBoundsWithTitle(5, 1, new Point(0, 0));
  }

  public int encodeBand(Element bandElement) {
    Rectangle boxRectangle = getExtents(basicsEncoder);
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Willpower");
    int yOffset = willpowerEncoder.encodeWillpower(bandElement, textRect.y + 3, textRect.x, textRect.width, 4, 10);
    yOffset += LINE_HEIGHT;
    yOffset += addShapeRow(bandElement, TAG_RECTANGLE, textRect.y + yOffset, textRect.x, 10, textRect.width, 4);
    yOffset += 4;
    yOffset = naturePartEncoder.addNaturePart(bandElement, textRect, yOffset);
    return textRect.height + TITLE_HEIGHT + TEXT_PADDING * 2;
  }

  public String getGroupName() {
    return "VoidstateDefaultWillpowerSubreport";
  }
}