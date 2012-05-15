package net.sf.anathema.character.ghost.passions.model;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;

public interface IPassion extends ISubTrait {

  ITraitReference getTraitReference();

}
