package net.sf.anathema.character.main.magic.charm.special.charms;

import net.sf.anathema.character.main.magic.charm.special.charms.ISpecialCharm;
import net.sf.anathema.character.main.traits.TraitType;

public interface ITraitCapModifyingCharm extends ISpecialCharm
{
	TraitType getTraitType();
	
	int getModifier();
}
