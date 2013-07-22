package net.sf.anathema.hero.charms.model.special.traitcap;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

public interface ITraitCapModifyingCharm extends ISpecialCharm
{
	TraitType getTraitType();
	
	int getModifier();
}
