package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.traits.ITraitType;
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

  public ICasteType[] getCasteTypes() {
    return null;
  }

	@Override
	public ICasteType getGroupCasteType()
	{
		return null;
	}
	
	@Override
	public ICasteType[] getTraitCasteTypes(ITraitType type)
	{
		return null;
	}
}