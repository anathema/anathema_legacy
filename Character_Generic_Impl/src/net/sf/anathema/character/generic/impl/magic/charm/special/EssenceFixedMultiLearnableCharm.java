package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.magic.charm.special.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.generic.traits.ITraitType;

public class EssenceFixedMultiLearnableCharm extends TraitDependentMultiLearnableCharm
{

	public EssenceFixedMultiLearnableCharm(String charmId, int absoluteLearnLimit,
			ITraitType traitType) {
		super(charmId, absoluteLearnLimit, traitType);
	}
	
	public int getMinimumLearnCount(IGenericTraitCollection traitCollection)
	{
		return getMaximumLearnCount(traitCollection);
	}

}
