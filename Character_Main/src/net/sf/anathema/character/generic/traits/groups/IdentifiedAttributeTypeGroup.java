package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public class IdentifiedAttributeTypeGroup extends TraitTypeGroup implements IIdentifiedCasteTraitTypeGroup {

  private final AttributeGroupType groupType;

  public IdentifiedAttributeTypeGroup(AttributeGroupType groupType) {
    super(AttributeType.getAllFor(groupType));
    this.groupType = groupType;
  }

  @Override
  public AttributeGroupType getGroupId() {
    return groupType;
  }

  @Override
  public CasteType[] getTraitCasteTypes(TraitType type) {
    return new CasteType[0];
  }
}