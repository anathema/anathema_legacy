package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.awt.Rectangle;

import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;

public class VoidstateTenAbilityGroupPageEncoder extends AbstractVoidstateAbilityGroupPageEncoder {

  public VoidstateTenAbilityGroupPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    super(basicsEncoder, 10);
  }

  public String getGroupName() {
    return "VoidstateFiveAbilityGroupSubreport";
  }

  public static Rectangle calculateExtents(VoidstateBasicsEncoder basicsEncoder) {
    return calculateExtents(basicsEncoder, 10);
  }
}