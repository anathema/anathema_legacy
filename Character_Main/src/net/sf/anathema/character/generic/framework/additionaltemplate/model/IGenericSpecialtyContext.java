package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.specialties.Specialty;
import net.sf.anathema.lib.control.IChangeListener;

public interface IGenericSpecialtyContext {

  Specialty[] getSpecialties(ITraitType traitType);

  void addSpecialtyListChangeListener(IChangeListener listener);
}
