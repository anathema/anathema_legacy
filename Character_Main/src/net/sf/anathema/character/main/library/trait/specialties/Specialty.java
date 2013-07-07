package net.sf.anathema.character.main.library.trait.specialties;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.library.trait.Trait;

public interface Specialty extends Trait {

  String getName();

  TraitType getBasicTraitType();
}