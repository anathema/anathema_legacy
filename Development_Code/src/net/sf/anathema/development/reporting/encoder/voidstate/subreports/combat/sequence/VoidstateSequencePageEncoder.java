package net.sf.anathema.development.reporting.encoder.voidstate.subreports.combat.sequence;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;

import org.dom4j.Element;

public class VoidstateSequencePageEncoder extends AbstractVoidstateSequencePageEncoder {

  private final VoidstateBasicsEncoder basicsEncoder;

  public VoidstateSequencePageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    this.basicsEncoder = basicsEncoder;
  }

  public int encodeBand(Element bandElement) {
    Rectangle bounds = calculateBounds(basicsEncoder);
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, bounds, "Combat Sequence");
    addTextElement(
        bandElement,
        getSequenceText(),
        FONT_SIZE - 1,
        VALUE_LEFT,
        textRect.x,
        textRect.y,
        textRect.width - 10,
        LINE_HEIGHT);
    return bounds.height;
  }

  private String getSequenceText() {
    String minimumDamage = ParameterUtilities.parameterString(ICharacterReportConstants.MINIMUM_DAMAGE) + methodCall("intValue");
    return quotify("1) Attack Roll  2) Subtract Penalties (shields, cover, etc.)  3) Defence Roll (if successes remain)  4) Determine Damage  5) Check For Knockdown  6) Apply Soak  7) Roll Damage (minimum =")
        + "+"
        + minimumDamage
        + "+"
        + quotify(")  8) Apply Damage  9) Check For Stun");
  }

  public String getGroupName() {
    return "VoidStateSequenceSubreport";
  }
}