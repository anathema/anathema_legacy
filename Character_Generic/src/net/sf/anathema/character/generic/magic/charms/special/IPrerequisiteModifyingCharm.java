package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IPrerequisiteModifyingCharm extends ISpecialCharm
{
	public int getTraitModifier(ICharm charm, ITraitType trait, int value);
}
