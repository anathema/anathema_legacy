package net.sf.anathema.hero.specialties;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitType;

public interface Specialty extends Trait {

  String getName();

  TraitType getBasicTraitType();
}