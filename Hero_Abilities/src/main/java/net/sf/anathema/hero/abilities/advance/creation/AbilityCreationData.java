package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.hero.template.creation.BonusPointCosts;
import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.template.points.IAbilityCreationPoints;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.abilities.template.AbilityPointsTemplate;
import net.sf.anathema.hero.traits.advance.TraitListCreationData;

public class AbilityCreationData implements TraitListCreationData {

  private AbilityPointsTemplate template;
  private IAbilityCreationPoints abilityCreationPoints;
  private BonusPointCosts costs;


  public AbilityCreationData(AbilityPointsTemplate template, IAbilityCreationPoints abilityCreationPoints, BonusPointCosts costs) {
    this.template = template;
    this.abilityCreationPoints = abilityCreationPoints;
    this.costs = costs;
  }

  @Override
  public int getCalculationBase(TraitType type) {
    return template.standard.calculationBase;
  }

  public CurrentRatingCosts getAbilityCosts(boolean casteOrFavored) {
    return costs.getAbilityCosts(casteOrFavored);
  }

  public int getMaximumFreeAbilityRank() {
    return costs.getMaximumFreeAbilityRank();
  }

  public int getDefaultDotCount() {
    return abilityCreationPoints.getDefaultDotCount();
  }

  public int getFavoredDotCount() {
    return abilityCreationPoints.getFavoredDotCount();
  }
}
