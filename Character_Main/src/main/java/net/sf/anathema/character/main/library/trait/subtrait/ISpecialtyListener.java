package net.sf.anathema.character.main.library.trait.subtrait;

import net.sf.anathema.character.main.library.trait.specialties.Specialty;

public interface ISpecialtyListener {

  void subTraitValueChanged();

  void subTraitAdded(Specialty subTrait);

  void subTraitRemoved(Specialty subTrait);
}