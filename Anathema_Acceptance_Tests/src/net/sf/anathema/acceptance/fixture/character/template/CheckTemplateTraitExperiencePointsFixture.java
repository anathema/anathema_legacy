package net.sf.anathema.acceptance.fixture.character.template;

import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.library.trait.experience.TraitRatingCostCalculator;

public class CheckTemplateTraitExperiencePointsFixture extends AbstractTemplateColumnFixture {

  public int creationValue;
  public int experiencedValue;
  public boolean favored;

  private IExperiencePointCosts getExperiencePointCosts() {
    return getTemplate().getExperienceCost();
  }

  private int getRatingBasedCost(ICurrentRatingCosts ratingCosts) {
    return TraitRatingCostCalculator.getTraitRaitingCosts(creationValue, experiencedValue, ratingCosts);
  }

  public int essenceCost() {
    return getRatingBasedCost(getExperiencePointCosts().getEssenceCosts());
  }

  public int abilityCost() {
    return getRatingBasedCost(getExperiencePointCosts().getAbilityCosts(favored));
  }

  public int attributeCost() {
    return getRatingBasedCost(getExperiencePointCosts().getAttributeCosts(favored));
  }

  public int willpowerCost() {
    return getRatingBasedCost(getExperiencePointCosts().getWillpowerCosts());
  }

  public int virtueCost() {
    return getRatingBasedCost(getExperiencePointCosts().getVirtueCosts());
  }

  public int specialtyCost() {
    return getExperiencePointCosts().getSpecialtyCosts(favored);
  }
}