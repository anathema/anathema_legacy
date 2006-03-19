package net.sf.anathema.development.reporting.encoder.voidstate.subreports.health;

import org.dom4j.Element;

public class VoidstateLunarHealthPageEncoder extends AbstractVoidstateHealthEncoder {

  public int encodeBand(Element bandElement) {
    int width = 162;
    encodeSoak(bandElement, width, false);
    encodeHealthRectangleBox(bandElement, 27, width, LINE_HEIGHT * 7 - 2, 1, 4);
    return calculateBounds().height;
  }

  public String getGroupName() {
    return "VoidstateLunarHealthSubreport";
  }

  @Override
  protected int getSoakLineWidth() {
    return 25;
  }

  @Override
  protected int getLineStartX() {
    return 75;
  }
}