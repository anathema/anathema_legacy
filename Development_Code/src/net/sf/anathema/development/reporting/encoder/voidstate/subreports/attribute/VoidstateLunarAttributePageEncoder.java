package net.sf.anathema.development.reporting.encoder.voidstate.subreports.attribute;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.util.IAttributesEncoder;

import org.dom4j.Element;

public class VoidstateLunarAttributePageEncoder extends AbstractVoidstateHumanAttributePageEncoder {

  public VoidstateLunarAttributePageEncoder(VoidstateBasicsEncoder basicsEncoder, IAttributesEncoder attributesEncoder) {
    super(basicsEncoder, attributesEncoder);
  }

  public String getGroupName() {
    return "VoidstateLunarAttributeSubreport";
  }

  @Override
  protected int encodeAttributeDots(
      Element bandElement,
      Rectangle textRectangle,
      int attributeHeight,
      AttributeGroupType type) {
    return getAttributesEncoder().encodeAttributeGroup(
        bandElement,
        AttributeType.getAllFor(type),
        textRectangle.y + attributeHeight,
        textRectangle.x,
        textRectangle.width - 7);
  }
}