package net.sf.anathema.character.impl.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.AbilityPointCosts;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.main.model.traits.TraitMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecialtyCalculator {

  private final TraitMap abilityModel;
  private final int specialtyPoints;

  public SpecialtyCalculator(TraitMap abilityModel, int specialtyPoints) {
    this.abilityModel = abilityModel;
    this.specialtyPoints = specialtyPoints;
  }

  public int getSpecialtyPointsSpent(IGenericSpecialty[] specialties) {
    return Math.min(specialtyPoints, specialties.length);
  }

  public int getSpecialtyCosts(IGenericSpecialty[] specialties) {
    List<IGenericSpecialty> favoredSpecialties = getFavoredSpecialties(Arrays.asList(specialties));
    List<IGenericSpecialty> unfavoredSpecialties = new ArrayList<>(Arrays.asList(specialties));
    unfavoredSpecialties.removeAll(favoredSpecialties);
    int favoredCount = favoredSpecialties.size();
    int unfavoredCount = unfavoredSpecialties.size();

    unfavoredCount -= specialtyPoints;
    if (unfavoredCount < 0) {
      favoredCount = Math.max(favoredCount + unfavoredCount, 0);
      unfavoredCount = 0;
    }
    return unfavoredCount + (int) Math.ceil(favoredCount * 0.5);
  }

  private List<IGenericSpecialty> getFavoredSpecialties(List<IGenericSpecialty> specialties) {
    List<IGenericSpecialty> favoredSpecialties = new ArrayList<>();
    for (IGenericSpecialty specialty : specialties) {
      TraitType type = specialty.getBasicTrait().getType();
      if (abilityModel.getTrait(type).isCasteOrFavored()) {
        favoredSpecialties.add(specialty);
      }
    }
    return favoredSpecialties;
  }

  public void filterAffordableSpecialties(List<IGenericSpecialty> specialties, int availablePoints, AbilityPointCosts costs) {
    int overhead = getSpecialtyCosts(specialties) - availablePoints;
    if (overhead <= 0 || availablePoints == 0) {
      return;
    }
    List<IGenericSpecialty> favoredSpecialties = getFavoredSpecialties(specialties);
    List<IGenericSpecialty> unfavoredSpecialties = new ArrayList<>(specialties);
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
    List<IGenericSpecialty> affordableSpecialties = new ArrayList<>();
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