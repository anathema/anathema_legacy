package net.sf.anathema.character.model.advance;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.experience.TraitRatingCostCalculator;
import net.sf.anathema.character.main.model.traits.GenericTraitCollectionFacade;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.hero.model.Hero;

public class ExperiencePointCostCalculator implements IPointCostCalculator {

  private final IExperiencePointCosts costs;

  public ExperiencePointCostCalculator(IExperiencePointCosts costs) {
    this.costs = costs;
  }

  protected int getTraitRatingCosts(Trait trait, CurrentRatingCosts ratingCosts) {
    return TraitRatingCostCalculator.getTraitRatingCosts(trait, ratingCosts);
  }

  @Override
  public int getAbilityCosts(Trait ability, final boolean favored) {
     return getTraitRatingCosts(ability, costs.getAbilityCosts(favored));
  }

  @Override
  public int getAttributeCosts(Trait attribute, boolean favored) {
    return getTraitRatingCosts(attribute, costs.getAttributeCosts(favored));
  }

  @Override
  public int getEssenceCosts(Trait essence) {
    return getTraitRatingCosts(essence, costs.getEssenceCosts());
  }

  @Override
  public int getVirtueCosts(Trait virtue) {
    return getTraitRatingCosts(virtue, costs.getVirtueCosts());
  }

  @Override
  public int getWillpowerCosts(Trait willpower) {
    return getTraitRatingCosts(willpower, costs.getWillpowerCosts());
  }

  @Override
  public double getSpecialtyCosts(boolean favored) {
    return costs.getSpecialtyCosts(favored);
  }

  @Override
  public int getSpellCosts(Hero hero, ISpell spell, TraitMap traitMap) {
    return costs.getSpellCosts(spell, hero, new GenericTraitCollectionFacade(traitMap));
  }

  @Override
  public int getCharmCosts(Hero hero, ICharm charm, TraitMap traitMap) {
    return costs.getCharmCosts(charm, new CostAnalyzer(hero));
  }
}