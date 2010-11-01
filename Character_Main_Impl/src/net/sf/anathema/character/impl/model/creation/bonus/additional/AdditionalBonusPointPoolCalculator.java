package net.sf.anathema.character.impl.model.creation.bonus.additional;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.additionalrules.IAdditionalBonusPointPool;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.IAbilityPointCosts;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.impl.model.creation.bonus.ability.SpecialtyCalculator;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;

public class AdditionalBonusPointPoolCalculator {

  private final IAdditionalBonusPointPool poolTemplate;
  private int pointsSpent;
  private final SpecialtyCalculator specialtyCalculator;
  private final IGenericTraitCollection collection;

  public AdditionalBonusPointPoolCalculator(IAdditionalBonusPointPool poolTemplate, IGenericTraitCollection collection) {
    this.poolTemplate = poolTemplate;
    this.collection = collection;
    this.specialtyCalculator = new SpecialtyCalculator(collection, 0);
  }

  public void spendPoints(int pointsToSpent) {
    Ensure.ensureTrue("Too many bonuspoints spent.", pointsSpent <= getRemainingPoints()); //$NON-NLS-1$
    pointsToSpent += pointsToSpent;
  }

  public int getRemainingPoints() {
    return poolTemplate.getAmount(collection) - pointsSpent;
  }

  public int getAmount() {
    return poolTemplate.getAmount(collection);
  }

  public void reset() {
    this.pointsSpent = 0;
  }

  public int spend(IGenericTrait trait, int pointsToSpent) {
    if (trait.getType() == null || !poolTemplate.isAllowedForTrait(collection, trait)) {
      return 0;
    }
    int availableBonusPoints = Math.min(pointsToSpent, getRemainingPoints());
    pointsSpent += availableBonusPoints;
    return availableBonusPoints;
  }

  public int spend(IMagic magic, int pointsToSpent) {
    if (!poolTemplate.isAllowedForMagic(collection, magic)) {
      return 0;
    }
    int availableBonusPoints = Math.min(pointsToSpent, getRemainingPoints());
    pointsSpent += availableBonusPoints;
    return availableBonusPoints;
  }

  public int spend(List<IGenericSpecialty> allSpecialties, IAbilityPointCosts costs) {
    List<IGenericSpecialty> allowedSpecialties = new ArrayList<IGenericSpecialty>();
    for (IGenericSpecialty specialty : allSpecialties) {
      if (poolTemplate.isAllowedForTrait(collection, specialty.getBasicTrait())) {
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

  public boolean isFavoredBackground(IDefaultTrait background) {
    return poolTemplate.isAllowedForTrait(collection, background);
  }
}