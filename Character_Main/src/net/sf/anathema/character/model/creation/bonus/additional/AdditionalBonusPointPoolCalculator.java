package net.sf.anathema.character.model.creation.bonus.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalBonusPointPool;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.AbilityPointCosts;
import net.sf.anathema.character.generic.traits.ValuedTraitType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.model.creation.bonus.ability.SpecialtyCalculator;

import java.util.ArrayList;
import java.util.List;

public class AdditionalBonusPointPoolCalculator {

  private final IAdditionalBonusPointPool poolTemplate;
  private int pointsSpent;
  private final SpecialtyCalculator specialtyCalculator;
  private final TraitMap traitMap;

  public AdditionalBonusPointPoolCalculator(IAdditionalBonusPointPool poolTemplate, TraitMap traitMap) {
    this.poolTemplate = poolTemplate;
    this.traitMap = traitMap;
    this.specialtyCalculator = new SpecialtyCalculator(traitMap, 0);
  }

  public int getRemainingPoints() {
    return poolTemplate.getAmount(traitMap) - pointsSpent;
  }

  public int getAmount() {
    return poolTemplate.getAmount(traitMap);
  }

  public void reset() {
    this.pointsSpent = 0;
  }

  public int spend(ValuedTraitType trait, int pointsToSpent) {
    if (trait.getType() == null || !poolTemplate.isAllowedForTrait(traitMap, trait)) {
      return 0;
    }
    int availableBonusPoints = Math.min(pointsToSpent, getRemainingPoints());
    pointsSpent += availableBonusPoints;
    return availableBonusPoints;
  }

  public int spend(IMagic magic, int pointsToSpent) {
    if (!poolTemplate.isAllowedForMagic(traitMap, magic)) {
      return 0;
    }
    int availableBonusPoints = Math.min(pointsToSpent, getRemainingPoints());
    pointsSpent += availableBonusPoints;
    return availableBonusPoints;
  }

  public int spend(List<IGenericSpecialty> allSpecialties, AbilityPointCosts costs) {
    List<IGenericSpecialty> allowedSpecialties = new ArrayList<>();
    for (IGenericSpecialty specialty : allSpecialties) {
      if (poolTemplate.isAllowedForTrait(traitMap, specialty.getBasicTrait())) {
        allowedSpecialties.add(specialty);
      }
    }
    specialtyCalculator.filterAffordableSpecialties(allowedSpecialties, getRemainingPoints(), costs);
    int specialtyCosts = getSpecialtyCosts(allowedSpecialties);
    allSpecialties.removeAll(allowedSpecialties);
    pointsSpent += specialtyCosts;
    return specialtyCosts;
  }

  public int getSpecialtyCosts(List<IGenericSpecialty> specialties) {
    return specialtyCalculator.getSpecialtyCosts(specialties.toArray(new IGenericSpecialty[specialties.size()]));
  }

  public boolean isFavoredBackground(Trait background) {
    return poolTemplate.isAllowedForTrait(traitMap, background);
  }
}