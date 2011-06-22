package net.sf.anathema.character.spirit.template;

import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class SpiritAttributeCreationPoints extends ReflectionCloneableObject<IAttributeCreationPoints>
	implements IAttributeCreationPoints
{
	private final String CULT_STRING = "Cult";
	
	ICoreTraitConfiguration traits;

	@Override
	public int getCount(AttributeGroupPriority priority) {
		if (priority == AttributeGroupPriority.Primary)
			return getPrimaryCount();
		else
			return 0;
	}

	@Override
	public int[] getCounts() {
		return new int[] { getPrimaryCount(), 0, 0 };
	}

	@Override
	public int getFavorableTraitCount()
	{
		return 0;
	}

	@Override
	public int getPrimaryCount()
	{
		return 0;
	}

	@Override
	public int getSecondaryCount()
	{
		return 0;
	}

	@Override
	public int getTertiaryCount()
	{
		return 0;
	}

	@Override
	public int getDefaultDotCount()
	{
		return 0;
	}

	@Override
	public int getExtraFavoredDotCount()
	{
		return 0;
	}
	
	@Override
	public int getExtraGenericDotCount()
	{
		try
		{
			int essence = traits.getTrait(OtherTraitType.Essence).getCurrentValue();
			int cult = 0;
			for (IDefaultTrait backgrounds : traits.getBackgrounds().getBackgrounds())
				if (backgrounds.getType().toString().equals(CULT_STRING))
					cult = backgrounds.getCurrentValue();
			int virtueSum = traits.getTrait(VirtueType.Compassion).getCurrentValue() +
							traits.getTrait(VirtueType.Conviction).getCurrentValue() +
							traits.getTrait(VirtueType.Temperance).getCurrentValue() +
							traits.getTrait(VirtueType.Valor).getCurrentValue();
			return 3 * essence + 3 * cult + 2 * virtueSum;
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	@Override
	public int getFavoredDotCount()
	{
		return 0;
	}
	
	public void informTraits(Object traits)
	{
		this.traits = ((ICharacterStatistics)traits).getTraitConfiguration();
	}
}
