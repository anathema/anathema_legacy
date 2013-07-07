package net.sf.anathema.character.main.traits;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.library.trait.specialties.Specialty;
import net.sf.anathema.lib.control.ChangeListener;

public interface SpecialtiesCollection {

  Specialty[] getSpecialties(TraitType traitType);

  void addSpecialtyListChangeListener(ChangeListener listener);
}
