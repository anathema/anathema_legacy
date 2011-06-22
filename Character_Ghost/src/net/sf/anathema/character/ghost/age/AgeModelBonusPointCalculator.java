package net.sf.anathema.character.ghost.age;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class AgeModelBonusPointCalculator implements IAdditionalModelBonusPointCalculator
{
	private final ICharacterModelContext context;
	
	public AgeModelBonusPointCalculator(ICharacterModelContext context)
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
		IGenericTrait ageTrait = context.getTraitCollection().getTrait(new CustomizedBackgroundTemplate("Age"));
		return ageTrait == null ? 0 : 5 * ageTrait.getCurrentValue();
	}

	@Override
	public void recalculate() 
	{
	}

}
