package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.Trait;

public interface Specialty extends Trait {

  String getName();

  TraitType getBasicTraitType();
}