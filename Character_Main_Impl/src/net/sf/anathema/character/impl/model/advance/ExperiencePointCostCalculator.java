package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IModifiableBasicTrait;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.library.trait.experience.TraitRatingCostCalculator;

public class ExperiencePointCostCalculator implements IPointCostCalculator {

  private final IExperiencePointCosts costs;

  public ExperiencePointCostCalculator(IExperiencePointCosts costs) {
    this.costs = costs;
  }

  protected int getTraitRatingCosts(IModifiableBasicTrait trait, ICurrentRatingCosts ratingCosts) {
    return TraitRatingCostCalculator.getTraitRatingCosts(trait, ratingCosts);
  }

  public int getAbilityCosts(IModifiableBasicTrait ability, boolean favored) {
    return getTraitRatingCosts(ability, costs.getAbilityCosts(favored));
  }

  public int getAttributeCosts(IModifiableBasicTrait attribute) {
    return getTraitRatingCosts(attribute, costs.getAttributeCosts());
  }

  public int getEssenceCosts(IModifiableBasicTrait essence) {
    return getTraitRatingCosts(essence, costs.getEssenceCosts());
  }

  public int getVirtueCosts(IModifiableBasicTrait virtue) {
    return getTraitRatingCosts(virtue, costs.getVirtueCosts());
  }

  public int getWillpowerCosts(IModifiableBasicTrait willpower) {
    return getTraitRatingCosts(willpower, costs.getWillpowerCosts());
  }

  public int getComboCosts(ICharm[] charms) {
    return costs.getComboCosts(charms);
  }

  public double getSpecialtyCosts(boolean favored) {
    return costs.getSpecialtyCosts(favored);
  }

  public int getSpellCosts(ISpell spell, IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection, FavoringTraitType type) {
    return costs.getSpellCosts(spell, basicCharacter, traitCollection, type);
  }

  public int getCharmCosts(ICharm charm, IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection, FavoringTraitType type) {
    return costs.getCharmCosts(charm, new CostAnalyzer(basicCharacter, traitCollection, type));
  }
}