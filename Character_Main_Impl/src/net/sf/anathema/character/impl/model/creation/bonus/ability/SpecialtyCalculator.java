package net.sf.anathema.character.impl.model.creation.bonus.ability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.IAbilityPointCosts;

public class SpecialtyCalculator {

  private final IGenericTraitCollection traitConfiguration;

  public SpecialtyCalculator(IGenericTraitCollection traitConfiguration) {
    this.traitConfiguration = traitConfiguration;
  }

  public int getSpecialtyCosts(IGenericSpecialty[] specialties) {
    List<IGenericSpecialty> favoredSpecialties = getFavoredSpecialties(Arrays.asList(specialties));
    List<IGenericSpecialty> unfavoredSpecialties = new ArrayList<IGenericSpecialty>(Arrays.asList(specialties));
    unfavoredSpecialties.removeAll(favoredSpecialties);
    int favoredCount = favoredSpecialties.size();
    int unfavoredCount = unfavoredSpecialties.size();
    return unfavoredCount + (int) Math.ceil(favoredCount * 0.5);
  }

  private List<IGenericSpecialty> getFavoredSpecialties(List<IGenericSpecialty> specialties) {
    List<IGenericSpecialty> favoredSpecialties = new ArrayList<IGenericSpecialty>();
    for (IGenericSpecialty specialty : specialties) {
      if (traitConfiguration.getFavorableTrait(specialty.getBasicTrait().getType()).isCasteOrFavored()) {
        favoredSpecialties.add(specialty);
      }
    }
    return favoredSpecialties;
  }

  public void filterAffordableSpecialties(
      List<IGenericSpecialty> specialties,
      int availablePoints,
      IAbilityPointCosts costs) {
    int overhead = getSpecialtyCosts(specialties) - availablePoints;
    if (overhead <= 0 || availablePoints == 0) {
      return;
    }
    List<IGenericSpecialty> favoredSpecialties = getFavoredSpecialties(specialties);
    List<IGenericSpecialty> unfavoredSpecialties = new ArrayList<IGenericSpecialty>(specialties);
    unfavoredSpecialties.removeAll(favoredSpecialties);
    int favoredRest = favoredSpecialties.size() % costs.getFavoredSpecialtyDotsPerPoint();
    if (overhead > 0 && favoredRest > 0) {
      overhead--;
      removeSpecifiedNumber(favoredSpecialties, favoredRest);
    }
    while (overhead > 0 && favoredSpecialties.size() > 0) {
      removeSpecifiedNumber(favoredSpecialties, costs.getFavoredSpecialtyDotsPerPoint());
      overhead--;
    }
    int unfavoredRest = unfavoredSpecialties.size() % costs.getDefaultSpecialtyDotsPerPoint();
    if (overhead > 0 && unfavoredRest > 0) {
      overhead--;
      removeSpecifiedNumber(unfavoredSpecialties, unfavoredRest);
    }
    while (overhead > 0 && unfavoredSpecialties.size() > 0) {
      removeSpecifiedNumber(unfavoredSpecialties, costs.getDefaultSpecialtyDotsPerPoint());
      overhead--;
    }
    List<IGenericSpecialty> affordableSpecialties = new ArrayList<IGenericSpecialty>();
    affordableSpecialties.addAll(favoredSpecialties);
    affordableSpecialties.addAll(unfavoredSpecialties);
    specialties.retainAll(affordableSpecialties);
  }

  private void removeSpecifiedNumber(List<IGenericSpecialty> specialties, int count) {
    for (int i = 0; i < count; i++) {
      specialties.remove(0);
    }
  }

  private int getSpecialtyCosts(List<IGenericSpecialty> specialties) {
    return getSpecialtyCosts(specialties.toArray(new IGenericSpecialty[specialties.size()]));
  }
}