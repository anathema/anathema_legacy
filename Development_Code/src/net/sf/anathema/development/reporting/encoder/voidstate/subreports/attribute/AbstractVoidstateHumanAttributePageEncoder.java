package net.sf.anathema.development.reporting.encoder.voidstate.subreports.attribute;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.util.IAttributesEncoder;

import org.dom4j.Element;

public abstract class AbstractVoidstateHumanAttributePageEncoder extends AbstractVoidstateAttributePageEncoder {

  private final VoidstateBasicsEncoder basicsEncoder;
  private final IAttributesEncoder attributesEncoder;

  public AbstractVoidstateHumanAttributePageEncoder(
      VoidstateBasicsEncoder basicsEncoder,
      IAttributesEncoder attributesEncoder) {
    this.basicsEncoder = basicsEncoder;
    this.attributesEncoder = attributesEncoder;
  }

  public int encodeBand(Element bandElement) {
    Rectangle boxRectangle = basicsEncoder.createOneColumnlBoxBoundsWithoutTitle(9, 1, new Point(0, 0));
    Rectangle textRectangle = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Attributes");
    int attributeHeight = 0;
    attributeHeight = Math.max(attributeHeight, encodeAttributeDots(
        bandElement,
        textRectangle,
        attributeHeight,
        AttributeGroupType.Physical));
    attributeHeight += TEXT_PADDING;
    attributeHeight += encodeAttributeDots(bandElement, textRectangle, attributeHeight, AttributeGroupType.Social);
    attributeHeight += TEXT_PADDING;
    attributeHeight += encodeAttributeDots(bandElement, textRectangle, attributeHeight, AttributeGroupType.Mental);
    return boxRectangle.height;
  }

  protected int encodeAttributeDots(
      Element bandElement,
      Rectangle textRectangle,
      int attributeHeight,
      AttributeGroupType attributeGroupType) {
    return attributesEncoder.encodeAttributeGroup(
        bandElement,
        AttributeType.getAllFor(attributeGroupType),
        textRectangle.y + attributeHeight,
        textRectangle.x,
        textRectangle.width);
  }

  protected final IAttributesEncoder getAttributesEncoder() {
    return attributesEncoder;
  }
}