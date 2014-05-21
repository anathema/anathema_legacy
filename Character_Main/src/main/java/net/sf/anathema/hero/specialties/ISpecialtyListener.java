package net.sf.anathema.hero.specialties;

import net.sf.anathema.hero.specialties.Specialty;

public interface ISpecialtyListener {

  void subTraitValueChanged();

  void subTraitAdded(Specialty subTrait);

  void subTraitRemoved(Specialty subTrait);
}