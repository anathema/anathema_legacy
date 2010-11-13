package net.sf.anathema.character.generic.traits.types;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.traits.ITraitType;

public enum AttributeType implements ITraitType {

  Strength(AttributeGroupType.Physical), Dexterity(AttributeGroupType.Physical), Stamina(AttributeGroupType.Physical),

  Charisma(AttributeGroupType.Social), Manipulation(AttributeGroupType.Social), Appearance(AttributeGroupType.Social),

  Perception(AttributeGroupType.Mental), Intelligence(AttributeGroupType.Mental), Wits(AttributeGroupType.Mental);

  public static AttributeType[] getAllFor(AttributeGroupType groupType) {
    List<AttributeType> foundTypes = new ArrayList<AttributeType>();
    AttributeType[] allTypes = values();
    for (AttributeType type : allTypes) {
      if (type.getGroupType() == groupType) {
        foundTypes.add(type);
      }
    }
    return foundTypes.toArray(new AttributeType[foundTypes.size()]);
  }

  private final AttributeGroupType groupType;

  private AttributeType(AttributeGroupType groupType) {
    this.groupType = groupType;
  }

  public String getId() {
    return name();
  }

  private AttributeGroupType getGroupType() {
    return groupType;
  }

  public void accept(ITraitTypeVisitor visitor) {
    visitor.visitAttribute(this);
  }

  @Override
  public String toString() {
    return getId();
  }
}