package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.specialties.Specialty;
import net.sf.anathema.lib.control.ChangeListener;

public interface SpecialtiesCollection {

  Specialty[] getSpecialties(TraitType traitType);

  void addSpecialtyListChangeListener(ChangeListener listener);
}
