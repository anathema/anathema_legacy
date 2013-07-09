package net.sf.anathema.character.main.advance;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.experience.TraitRatingCostCalculator;
import net.sf.anathema.hero.traits.GenericTraitCollectionFacade;
import net.sf.anathema.hero.traits.TraitMap;
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
  public int getCharmCosts(Hero hero, Charm charm, TraitMap traitMap) {
    return costs.getCharmCosts(charm, new CostAnalyzer(hero));
  }
}