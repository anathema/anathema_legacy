package net.sf.anathema.hero.specialties.points.creation;

import net.sf.anathema.character.main.template.creation.IGenericSpecialty;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.specialties.SpecialtiesModel;
import net.sf.anathema.character.main.library.trait.specialties.SpecialtiesModelFetcher;
import net.sf.anathema.character.main.library.trait.specialties.Specialty;
import net.sf.anathema.character.main.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;

import java.util.ArrayList;
import java.util.List;

public class SpecialtiesCostCalculator implements HeroBonusPointCalculator {

  private SpecialtyCalculator specialtyCalculator;
  private Hero hero;
  private TraitMap traitMap;
  private int specialtyBonusPointCosts;
  private int specialtyDotSum;

  public SpecialtiesCostCalculator(Hero hero, TraitMap traitMap, int specialtyPoints) {
    this.hero = hero;
    this.traitMap = traitMap;
    this.specialtyCalculator = new SpecialtyCalculator(traitMap, specialtyPoints);
  }

  @Override
  public void recalculate() {
    clear();
    IGenericSpecialty[] specialties = createGenericSpecialties();
    specialtyDotSum = specialtyCalculator.getSpecialtyPointsSpent(specialties);
    specialtyBonusPointCosts = specialtyCalculator.getSpecialtyCosts(specialties);
  }

  private void clear() {
    specialtyDotSum = 0;
    specialtyBonusPointCosts = 0;
  }

  private IGenericSpecialty[] createGenericSpecialties() {
    List<IGenericSpecialty> specialties = new ArrayList<>();
    for (Trait ability : traitMap.getAll()) {
      SpecialtiesModel specialtiesModel = SpecialtiesModelFetcher.fetch(hero);
      ISubTraitContainer specialtiesContainer = specialtiesModel.getSpecialtiesContainer(ability.getType());
      for (Specialty specialty : specialtiesContainer.getSubTraits()) {
        for (int index = 0; index < specialty.getCalculationValue(); index++) {
          specialties.add(new GenericSpecialty(ability));
        }
      }
    }
    return specialties.toArray(new IGenericSpecialty[specialties.size()]);
  }

  @Override
  public int getBonusPointCost() {
    return specialtyBonusPointCosts;
  }

  public int getFreePointsSpent() {
    return specialtyDotSum;
  }

  @Override
  public int getBonusPointsGranted() {
    return 0;
  }
}
