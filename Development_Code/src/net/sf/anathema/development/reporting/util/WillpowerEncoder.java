package net.sf.anathema.development.reporting.util;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;

import org.dom4j.Element;

public class WillpowerEncoder {

  private final TraitEncoder traitEncoder = new TraitEncoder();

  public int encodeWillpower(Element parent, int y, int x, int width, int spacing, int maxWillpower) {
    return encodeWillpowerEllipseRow(parent, y, x, width, spacing, maxWillpower);
  }

  private int encodeWillpowerEllipseRow(Element parent, int y, int x, int width, int spacing, int count) {
    int size = (width - (spacing * (count - 1))) / count;
    for (int index = 0; index < count; index++) {
      traitEncoder.addEllipsePair(
          parent,
          x + spacing / 2 + index * (size + spacing),
          y,
          OtherTraitType.Willpower.getId(),
          index,
          size);
    }
    return size;
  }
}
