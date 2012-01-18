package net.sf.anathema.character.impl.model.creation.bonus.magic;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.magic.IUniqueRequiredCharmType;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;

public class UniqueRequiredCharmTypeModel extends AbstractSpendingModel
{
	private final MagicCostCalculator calculator;
	private final ICreationPoints creationPoints;
	
	public UniqueRequiredCharmTypeModel(IUniqueRequiredCharmType type, MagicCostCalculator magicCalculator, ICreationPoints creationPoints)
	{
		super("Charms", type == null ? "" : type.getName());
		this.creationPoints = creationPoints;
		this.calculator = magicCalculator;
	}

	@Override
	public int getAlotment() {
		return creationPoints.getUniqueRequiredCreationCharmCount();
	}

	@Override
	public int getSpentBonusPoints()
	{
		return 0;
	}

	@Override
	public Integer getValue()
	{
		return Math.min(calculator.getUniqueRequiredCharmTypePicksSpent(), getAlotment());
	}
}
