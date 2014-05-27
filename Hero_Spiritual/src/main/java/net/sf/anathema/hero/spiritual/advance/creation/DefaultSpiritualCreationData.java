package net.sf.anathema.hero.spiritual.advance.creation;

import net.sf.anathema.hero.spiritual.template.SpiritualPointsTemplate;
import net.sf.anathema.hero.template.creation.BonusPointCosts;
import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;

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

  @Override
  public CurrentRatingCosts getEssenceCost() {
    return costs.getEssenceCost();
  }

  @Override
  public int getWillpowerCost() {
    return costs.getWillpowerCosts();
  }
}