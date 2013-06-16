package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.LearnTrait;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.library.trait.experience.TraitRatingCostCalculator;
import net.sf.anathema.character.library.trait.IDefaultTrait;

public class ExperiencePointCostCalculator implements IPointCostCalculator {

  private final IExperiencePointCosts costs;

  public ExperiencePointCostCalculator(IExperiencePointCosts costs) {
    this.costs = costs;
  }

  protected int getTraitRatingCosts(LearnTrait trait, CurrentRatingCosts ratingCosts) {
    return TraitRatingCostCalculator.getTraitRatingCosts(trait, ratingCosts);
  }

  @Override
  public int getAbilityCosts(IDefaultTrait ability, final boolean favored) {
     return getTraitRatingCosts(ability, costs.getAbilityCosts(favored));
  }

  @Override
  public int getAttributeCosts(IDefaultTrait attribute, boolean favored) {
    return getTraitRatingCosts(attribute, costs.getAttributeCosts(favored));
  }

  @Override
  public int getEssenceCosts(IDefaultTrait essence) {
    return getTraitRatingCosts(essence, costs.getEssenceCosts());
  }

  @Override
  public int getVirtueCosts(IDefaultTrait virtue) {
    return getTraitRatingCosts(virtue, costs.getVirtueCosts());
  }

  @Override
  public int getWillpowerCosts(IDefaultTrait willpower) {
    return getTraitRatingCosts(willpower, costs.getWillpowerCosts());
  }

  @Override
  public double getSpecialtyCosts(boolean favored) {
    return costs.getSpecialtyCosts(favored);
  }

  @Override
  public int getSpellCosts(ISpell spell, IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection) {
    return costs.getSpellCosts(spell, basicCharacter, traitCollection);
  }

  @Override
  public int getCharmCosts(ICharm charm, IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection) {
    return costs.getCharmCosts(charm, new CostAnalyzer(basicCharacter, traitCollection));
  }
}