package net.sf.anathema.hero.traits.model.types;

import net.sf.anathema.hero.traits.model.TraitType;

import java.util.ArrayList;
import java.util.List;

public enum AttributeType implements TraitType {

  Strength(AttributeGroupType.Physical), Dexterity(AttributeGroupType.Physical), Stamina(AttributeGroupType.Physical),

  Charisma(AttributeGroupType.Social), Manipulation(AttributeGroupType.Social), Appearance(AttributeGroupType.Social),

  Perception(AttributeGroupType.Mental), Intelligence(AttributeGroupType.Mental), Wits(AttributeGroupType.Mental);

  public static AttributeType[] getAllFor(AttributeGroupType groupType) {
    List<AttributeType> foundTypes = new ArrayList<>();
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

  @Override
  public String getId() {
    return name();
  }

  private AttributeGroupType getGroupType() {
    return groupType;
  }

  @Override
  public void accept(ITraitTypeVisitor visitor) {
    visitor.visitAttribute(this);
  }

  @Override
  public String toString() {
    return getId();
  }
}