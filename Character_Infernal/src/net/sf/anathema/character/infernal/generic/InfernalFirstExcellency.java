package net.sf.anathema.character.infernal.generic;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.magic.charm.special.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.generic.traits.ITraitType;

public class InfernalFirstExcellency extends TraitDependentMultiLearnableCharm
{

	public InfernalFirstExcellency(String charmId, int absoluteLearnLimit,
			ITraitType traitType) {
		super(charmId, absoluteLearnLimit, traitType);
	}
	
	public int getMinimumLearnCount(IGenericTraitCollection traitCollection)
	{
		return getMaximumLearnCount(traitCollection);
	}

}
