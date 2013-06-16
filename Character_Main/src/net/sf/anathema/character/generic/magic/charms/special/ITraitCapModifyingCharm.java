package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.traits.TraitType;

public interface ITraitCapModifyingCharm extends ISpecialCharm
{
	TraitType getTraitType();
	
	int getModifier();
}
