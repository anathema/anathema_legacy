package net.sf.anathema.character.impl.model.context;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IGenericSpecialtyContext;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.specialties.Specialty;
import net.sf.anathema.lib.control.IChangeListener;

public class GenericSpecialtyContext implements IGenericSpecialtyContext {

  private final IGenericCharacter character;

  public GenericSpecialtyContext(IGenericCharacter character) {
    this.character = character;
  }

  @Override
  public Specialty[] getSpecialties(ITraitType traitType) {
    return character.getSpecialties(traitType);
  }

  @Override
  public void addSpecialtyListChangeListener(IChangeListener listener) {
    character.addSpecialtyListChangeListener(listener);
  }
}
