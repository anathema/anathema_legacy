package net.sf.anathema.development.reporting.encoder.voidstate.subreports.attribute;

import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.util.IAttributesEncoder;

public class VoidstateDefaultAttributePageEncoder extends AbstractVoidstateHumanAttributePageEncoder {

  public VoidstateDefaultAttributePageEncoder(VoidstateBasicsEncoder basicsEncoder, IAttributesEncoder attributesEncoder) {
    super(basicsEncoder, attributesEncoder);
  }

  public String getGroupName() {
    return "VoidstateDefaultAttributeSubreport";
  }
}