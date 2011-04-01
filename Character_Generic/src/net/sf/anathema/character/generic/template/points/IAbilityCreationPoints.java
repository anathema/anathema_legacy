package net.sf.anathema.character.generic.template.points;

import net.sf.anathema.lib.lang.clone.ICloneable;

public interface IAbilityCreationPoints extends ICloneable<IAbilityCreationPoints>, IFavorableTraitCreationPoints
{
	public void informTraits(Object traits);
}