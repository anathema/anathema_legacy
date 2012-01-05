package net.sf.anathema.character.impl.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;
import net.sf.anathema.character.library.trait.IFavorableTraitCostCalculator;
import net.sf.anathema.character.model.ICharacterStatistics;

public class DefaultAbilityBonusModel extends AbstractSpendingModel {
  private final IFavorableTraitCostCalculator abilityCalculator;
  private final ICreationPoints creationPoints;

  public DefaultAbilityBonusModel(IFavorableTraitCostCalculator abilityCalculator, ICreationPoints creationPoints)
  {
	  this(abilityCalculator, creationPoints, null);
  }
  
  public DefaultAbilityBonusModel(IFavorableTraitCostCalculator abilityCalculator, ICreationPoints creationPoints, ICharacterStatistics statistics) {
    super("Abilities", "General"); //$NON-NLS-1$ //$NON-NLS-2$
    this.abilityCalculator = abilityCalculator;
    this.creationPoints = creationPoints;
    
    if (statistics != null)
    	creationPoints.getAbilityCreationPoints().informTraits(statistics);
  }

  public Integer getValue() {
    return abilityCalculator.getFreePointsSpent(false);
  }

  public int getSpentBonusPoints() {
    return abilityCalculator.getBonusPointsSpent();
  }

  public int getAlotment() {
    return creationPoints.getAbilityCreationPoints().getDefaultDotCount();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}