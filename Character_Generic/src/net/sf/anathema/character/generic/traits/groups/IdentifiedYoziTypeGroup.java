package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.lib.util.IIdentificate;

public class IdentifiedYoziTypeGroup extends TraitTypeGroup implements IIdentifiedCasteTraitTypeGroup
{
	YoziType type;
	ICasteType caste;
	
	public IdentifiedYoziTypeGroup(YoziType type, ICasteType caste)
	{
		super(new YoziType[] { type });
		this.type = type;
		this.caste = caste;
	}

	@Override
	public ICasteType getGroupCasteType() {
		return caste;
	}

	@Override
	public ICasteType[] getTraitCasteTypes(ITraitType type) {
		return new ICasteType[] { caste };
	}

	@Override
	public IIdentificate getGroupId() {
		return type;
	}
}
