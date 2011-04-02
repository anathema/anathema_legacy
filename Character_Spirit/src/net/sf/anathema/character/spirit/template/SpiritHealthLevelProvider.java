package net.sf.anathema.character.spirit.template;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.health.IHealthLevelProvider;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class SpiritHealthLevelProvider implements IHealthLevelProvider
{
	ICoreTraitConfiguration traits;
	
	public SpiritHealthLevelProvider(ICharacterStatistics stats)
	{
		this.traits = stats.getTraitConfiguration();
	}

	@Override
	public int getHealthLevelTypeCount(HealthLevelType type)
	{
		
		int willpower = traits.getTrait(OtherTraitType.Willpower).getCurrentValue();
		int virtue = 0;
		virtue = Math.max(virtue, traits.getTrait(VirtueType.Compassion).getCurrentValue());
		virtue = Math.max(virtue, traits.getTrait(VirtueType.Conviction).getCurrentValue());
		virtue = Math.max(virtue, traits.getTrait(VirtueType.Temperance).getCurrentValue());
		virtue = Math.max(virtue, traits.getTrait(VirtueType.Valor).getCurrentValue());
		
		int levels = willpower + virtue - 2; //-0 and -4 count against the limit
		
		//additional -2 after calculation, to offset the base value given normally for mortals
		if (type == HealthLevelType.ONE)
			return (levels + 1) / 2 - 2;
		if (type == HealthLevelType.TWO)
			return (levels) / 2 - 2;
		
		return 0;
	}
}
