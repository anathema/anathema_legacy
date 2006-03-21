package net.sf.anathema.development.reporting.util;

import java.awt.Point;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;

import org.dom4j.Element;

public class AbilitiesEncoder extends AbstractJasperEncoder {

  private final TraitEncoder traitEncoder;

  public AbilitiesEncoder() {
    this(new TraitEncoder(8));
  }

  public AbilitiesEncoder(TraitEncoder encoder) {
    this.traitEncoder = encoder;
  }

  public int encodeAbilityGroup(Element parent, IIdentifiedTraitTypeGroup group, Point position, int width) {
    int height = 0;
    ITraitType[] traitTypes = group.getAllGroupTypes();
    for (int index = 0; index < traitTypes.length; index++) {
      String id = traitTypes[index].getId();
      Point traitPosition = new Point(position.x, position.y + (index * traitEncoder.getLineHeight()));
      height += traitEncoder.encodeWithRectangle(
          parent,
          id + "_boolean", id, traitPosition.x, traitPosition.y, width, TraitEncoder.MAX_DOT_COUNT); //$NON-NLS-1$
    }
    return height;
  }

  public int encodeAbilityGroup(
      Element parent,
      IIdentifiedTraitTypeGroup group,
      int y,
      int x,
      int width,
      int headerHeight) {
    int height = 0;
    addTextElement(parent, quotify(group.getGroupId().getId()), 8, VALUE_CENTER, x, y, width, headerHeight);
    height += headerHeight;
    y += headerHeight;
    height += encodeAbilityGroup(parent, group, new Point(x, y), width);
    return height;
  }

  public int encodeSpecialties(Element parent, Point position, int width, int rowCount, int dotCount) {
    int height = 0;
    for (int index = 0; index < rowCount; index++) {
      int currentY = position.y + (index * traitEncoder.getLineHeight());
      height += traitEncoder.encodeSpecialtyRow(parent, index, position.x, currentY, width, dotCount);
    }
    return height;
  }

  public int encodeSpecialties(Element parent, int y, int x, int width, int rowCount, int dotCount) {
    return encodeSpecialties(parent, new Point(x, y), width, rowCount, dotCount);
  }
}