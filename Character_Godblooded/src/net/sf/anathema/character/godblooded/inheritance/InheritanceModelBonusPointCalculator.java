package net.sf.anathema.character.godblooded.inheritance;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public class InheritanceModelBonusPointCalculator implements IAdditionalModelBonusPointCalculator
{
	private final ICharacterModelContext context;
	private final String INHERITANCE_STRING = "Inheritance";	
	int inheritanceBonusArray[] = { 0, 6, 12, 18, 24, 30 };
	
	public InheritanceModelBonusPointCalculator(ICharacterModelContext context)
	{
		this.context = context;
	}
	
	@Override
	public int getBonusPointCost()
	{
		return 0;
	}

	@Override
	public int getBonusPointsGranted() 
	{
		IGenericTrait inheritanceTrait = context.getTraitCollection().getTrait(new CustomizedBackgroundTemplate(INHERITANCE_STRING));
		IGenericTrait essenceTrait =   context.getTraitCollection().getTrait(OtherTraitType.Essence);
		int inheritanceBonus = inheritanceBonusArray[inheritanceTrait == null ? 0 : inheritanceTrait.getCurrentValue()];
		int essenceBonus = essenceTrait.getCurrentValue() > 1 ? 3 : 0;
		return inheritanceBonus + essenceBonus;
		
	}

	@Override
	public void recalculate() 
	{
	}

}
