package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.traits.TraitType;

public interface ITraitCapModifyingCharm extends ISpecialCharm
{
	TraitType getTraitType();
	
	int getModifier();
}
