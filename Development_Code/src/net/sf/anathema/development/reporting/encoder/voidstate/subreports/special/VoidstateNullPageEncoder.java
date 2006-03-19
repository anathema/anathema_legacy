package net.sf.anathema.development.reporting.encoder.voidstate.subreports.special;

import net.sf.anathema.development.reporting.encoder.voidstate.pages.AbstractVoidstatePageEncoder;

import org.dom4j.Element;

public class VoidstateNullPageEncoder extends AbstractVoidstatePageEncoder {

  public VoidstateNullPageEncoder(int pageWidth) {
    super(pageWidth);
  }

  public int encodeBand(Element bandElement) {
    return 0;
  }

  public String getGroupName() {
    return "VoidstateNullPage";
  }
}