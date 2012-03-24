package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IGenericSpecialtyContext {
  INamedGenericTrait[] getSpecialties(ITraitType traitType);

  void addSpecialtyListChangeListener(IChangeListener listener);
}
