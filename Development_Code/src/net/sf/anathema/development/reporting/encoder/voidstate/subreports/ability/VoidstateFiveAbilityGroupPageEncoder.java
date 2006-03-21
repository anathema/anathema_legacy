package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.awt.Rectangle;

import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;

public class VoidstateFiveAbilityGroupPageEncoder extends AbstractVoidstateAbilityGroupPageEncoder {

  public VoidstateFiveAbilityGroupPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    super(basicsEncoder, 5);
  }

  public String getGroupName() {
    return "VoidstateAbilityGroupSubreport";
  }

  public static Rectangle calculateExtents(VoidstateBasicsEncoder basicsEncoder) {
    return calculateExtents(basicsEncoder, 5);
  }
}