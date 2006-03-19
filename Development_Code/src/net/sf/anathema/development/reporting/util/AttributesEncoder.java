package net.sf.anathema.development.reporting.util;

import net.sf.anathema.character.generic.traits.types.AttributeType;

import org.dom4j.Element;

public class AttributesEncoder implements IAttributesEncoder {

  private final TraitEncoder traitEncoder;

  public AttributesEncoder() {
    this(new TraitEncoder());
  }

  public AttributesEncoder(TraitEncoder traitEncoder) {
    this.traitEncoder = traitEncoder;
  }

  public int encodeAttributeGroup(Element parent, AttributeType[] types, int y, int x, int width) {
    int maximumDotCount = TraitEncoder.MAX_DOT_COUNT;
    return encodeAttributeGroup(parent, types, y, x, width, maximumDotCount);
  }

  public int encodeAttributeGroup(Element parent, AttributeType[] types, int y, int x, int width, int maximumDotCount) {
    int height = 0;
    for (int index = 0; index < types.length; index++) {
      int traitY = y + (index * traitEncoder.getLineHeight());
      height += traitEncoder.encodeWithText(parent, types[index].getId(), x, traitY, width, maximumDotCount);
    }
    return height;
  }
}