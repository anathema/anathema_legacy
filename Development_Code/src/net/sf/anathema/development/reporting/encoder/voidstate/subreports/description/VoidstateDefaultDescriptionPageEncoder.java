package net.sf.anathema.development.reporting.encoder.voidstate.subreports.description;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.pages.VoidstateSecondPageEncoder;

import org.dom4j.Element;

public class VoidstateDefaultDescriptionPageEncoder extends AbstractVoidstateDescriptionPageEncoder {

  private final VoidstateBasicsEncoder basicsEncoder;

  public VoidstateDefaultDescriptionPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    this.basicsEncoder = basicsEncoder;
  }

  public int encodeBand(Element bandElement) {
    Point position = new Point(0, 1);
    Rectangle boxRectangle = basicsEncoder.createCorrectedTwoColumnBoxBoundsWithoutTitle(
        VoidstateSecondPageEncoder.LOWEST_LINE_COUNT,
        position);
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Character Description");
    basicsEncoder.addEmptyLines(bandElement, textRect, VoidstateSecondPageEncoder.LOWEST_LINE_COUNT);
    return boxRectangle.height + 1;
  }

  public String getGroupName() {
    return "VoidstateDefaultDescriptionSubreport";
  }
}