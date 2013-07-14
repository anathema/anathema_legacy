package net.sf.anathema.character.main.library.trait.specialties;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.traits.TraitType;

public interface Specialty extends Trait {

  String getName();

  TraitType getBasicTraitType();
}