package net.sf.anathema.hero.spiritual.advance.creation;

import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.hero.spiritual.template.SpiritualPointsTemplate;

public class DefaultSpiritualCreationData implements SpiritualCreationData {

  private SpiritualPointsTemplate template;
  private BonusPointCosts costs;

  public DefaultSpiritualCreationData(SpiritualPointsTemplate template, BonusPointCosts costs) {
    this.template = template;
    this.costs = costs;
  }

  @Override
  public int getCalculationBase(TraitType type) {
    if (type == OtherTraitType.Essence) {
      return template.essence.calculationBase;
    }
    if (type == OtherTraitType.Willpower) {
      return template.willpower.calculationBase;
    }
    return 1;
  }

  public int getFreeVirtueCreationDots() {
    return 5;
  }

  @Override
  public CurrentRatingCosts getVirtueCost() {
    return costs.getVirtueCosts();
  }

  @Override
  public CurrentRatingCosts getEssenceCost() {
    return costs.getEssenceCost();
  }

  @Override
  public int getWillpowerCost() {
    return costs.getWillpowerCosts();
  }

  @Override
  public int getMaximumFreeVirtueRank() {
    return costs.getMaximumFreeVirtueRank();
  }
}
