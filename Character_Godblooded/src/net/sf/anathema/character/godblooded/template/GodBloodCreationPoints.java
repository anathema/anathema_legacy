package net.sf.anathema.character.godblooded.template;

import net.sf.anathema.character.generic.framework.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class GodBloodCreationPoints extends GenericCreationPoints
{
	private final String INHERITANCE_STRING = "Inheritance";
	ICoreTraitConfiguration traits;
	int inheritanceBonusArray[] = { 0, 6, 12, 18, 24, 30 };
	
	@Override
	public int getBonusPointCount()
	{
		int baseAmount = super.getBonusPointCount();
		int inheritanceRank = 0;
		int essenceOffset = 0;
		for (IDefaultTrait background : traits.getBackgrounds().getBackgrounds())
			if (background.getType().toString().equals(INHERITANCE_STRING))
				inheritanceRank = background.getCurrentValue();
		
		if (traits.getTrait(OtherTraitType.Essence).getCurrentValue() > 1)
			essenceOffset = 3;
		
		return baseAmount + essenceOffset + inheritanceBonusArray[inheritanceRank];		
	}
	
	public void informTraits(Object traits)
	{
		this.traits = ((ICharacterStatistics)traits).getTraitConfiguration();
	}
}
