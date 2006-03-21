package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.awt.Point;

import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.traits.VoidStateAbilityEncoder;

import org.dom4j.Element;

public class VoidstateAbilityPageEncoder extends AbstractCharacterSheetPageEncoder {

  private final VoidStateAbilityEncoder encoder;

  public VoidstateAbilityPageEncoder(VoidStateAbilityEncoder encoder) {
    this.encoder = encoder;
  }

  public int encodeBand(Element bandElement) {
    return encoder.encodeAbilites(bandElement, new Point(0, 0));
  }

  public String getGroupName() {
    return "VoidstateAbilitySubreport";
  }
}