package net.sf.anathema.character.library.trait.subtrait;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.Trait;

public interface ISubTrait extends Trait {

  String getName();

  ITraitType getBasicTraitType();
}