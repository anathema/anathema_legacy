package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.traits.ITraitType;

public interface ITraitCapModifyingCharm extends ISpecialCharm
{
	ITraitType getTraitType();
	
	int getModifier();
}
