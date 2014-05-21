package net.sf.anathema.hero.equipment;

import net.sf.anathema.hero.specialties.SpecialtiesModel;
import net.sf.anathema.hero.specialties.SpecialtiesModelFetcher;
import net.sf.anathema.hero.specialties.Specialty;
import net.sf.anathema.hero.specialties.ISpecialtyListener;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.ChangeListener;

public class SpecialtiesCollectionImpl {

  private final Hero hero;

  public SpecialtiesCollectionImpl(Hero hero) {
    this.hero = hero;
  }

  public Specialty[] getSpecialties(TraitType traitType) {
    SpecialtiesModel specialtyConfiguration = SpecialtiesModelFetcher.fetch(hero);
    return specialtyConfiguration.getSpecialtiesContainer(traitType).getSubTraits();
  }

  public void addSpecialtyListChangeListener(final ChangeListener listener) {
    SpecialtiesModel config = SpecialtiesModelFetcher.fetch(hero);
    for (TraitType trait : config.getAllParentTraits()) {
      config.getSpecialtiesContainer(trait).addSubTraitListener(new ISpecialtyListener() {
        @Override
        public void subTraitValueChanged() {
        }

        @Override
        public void subTraitAdded(Specialty subTrait) {
          listener.changeOccurred();
        }

        @Override
        public void subTraitRemoved(Specialty subTrait) {
          listener.changeOccurred();
        }
      });
    }
  }
}
