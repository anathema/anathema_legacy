package net.sf.anathema.character.spirit.template;

import net.sf.anathema.character.generic.template.points.IAbilityCreationPoints;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class SpiritAbilityCreationPoints extends ReflectionCloneableObject<IAbilityCreationPoints>
	implements IAbilityCreationPoints
{
	ICoreTraitConfiguration traits;
	
	@Override
	public int getDefaultDotCount()
	{
		try
		{
			int essence = traits.getTrait(OtherTraitType.Essence).getCurrentValue();
			return 10 * essence;
		}
		catch (NullPointerException e)
		{
			return 0;
		}
	}

	@Override
	public int getExtraFavoredDotCount() {
		return 0;
	}
	
	@Override
	public int getExtraGenericDotCount() {
		return 0;
	}

	@Override
	public int getFavorableTraitCount() {
		return 1;
	}

	@Override
	public int getFavoredDotCount() {
		return 0;
	}

	@Override
	public void informTraits(Object traits) {
		this.traits = ((ICharacterStatistics)traits).getTraitConfiguration();
	}
}
