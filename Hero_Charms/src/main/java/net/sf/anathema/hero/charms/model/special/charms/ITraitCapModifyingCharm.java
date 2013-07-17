package net.sf.anathema.hero.charms.model.special.charms;

import net.sf.anathema.character.main.traits.TraitType;

public interface ITraitCapModifyingCharm extends ISpecialCharm
{
	TraitType getTraitType();
	
	int getModifier();
}
