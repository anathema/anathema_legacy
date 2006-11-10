package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public class IdentifiedAttributeTypeGroup extends TraitTypeGroup implements IIdentifiedCasteTraitTypeGroup {

  private final AttributeGroupType groupType;

  public IdentifiedAttributeTypeGroup(AttributeGroupType groupType) {
    super(AttributeType.getAllFor(groupType));
    this.groupType = groupType;
  }

  public AttributeGroupType getGroupId() {
    return groupType;
  }

  public ICasteType< ? extends ICasteTypeVisitor> getCasteType() {
    return null;
  }
}