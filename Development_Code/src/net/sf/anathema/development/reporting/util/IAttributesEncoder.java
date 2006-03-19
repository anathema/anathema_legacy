package net.sf.anathema.development.reporting.util;

import net.sf.anathema.character.generic.traits.types.AttributeType;

import org.dom4j.Element;

public interface IAttributesEncoder {

  public int encodeAttributeGroup(Element parent, AttributeType[] types, int y, int x, int width);

  public int encodeAttributeGroup(Element parent, AttributeType[] types, int y, int x, int width, int maximumDotCount);
}