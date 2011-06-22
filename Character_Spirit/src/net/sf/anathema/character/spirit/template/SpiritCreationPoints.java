package net.sf.anathema.character.spirit.template;

import net.sf.anathema.character.generic.framework.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class SpiritCreationPoints extends GenericCreationPoints
{
	ICoreTraitConfiguration traits;
	
	@Override
	public int getDefaultCreationCharmCount()
	{
		try
		{
			return traits.getTrait(OtherTraitType.Willpower).getCurrentValue();
		}
		catch (Exception e)
		{
			return 0;
		}
	}
	
	@Override
	public int getVirtueCreationPoints()
	{
		try
		{
			return 5 + traits.getTrait(OtherTraitType.Essence).getCurrentValue();
		}
		catch (Exception e)
		{
			return 0;
		}
	}
	
	public void informTraits(Object traits)
	{
		this.traits = ((ICharacterStatistics)traits).getTraitConfiguration();
	}
}
