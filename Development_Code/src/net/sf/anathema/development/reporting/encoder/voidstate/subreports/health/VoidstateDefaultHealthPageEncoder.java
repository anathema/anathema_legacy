package net.sf.anathema.development.reporting.encoder.voidstate.subreports.health;

import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;

import org.dom4j.Element;

public class VoidstateDefaultHealthPageEncoder extends AbstractVoidstateHealthEncoder implements
    IVoidStateFormatConstants {

  public int encodeBand(Element bandElement) {
    int width = 162;
    encodeSoak(bandElement, width, true);
    encodeHealthRectangleBox(bandElement, 36, width, LINE_HEIGHT * 6, 2, 2);
    return calculateBounds().height;
  }

  public String getGroupName() {
    return "VoidstateDefaultHealthSubreport";
  }

  @Override
  protected int getSoakLineWidth() {
    return 15;
  }

  @Override
  protected int getLineStartX() {
    return 90;
  }
}