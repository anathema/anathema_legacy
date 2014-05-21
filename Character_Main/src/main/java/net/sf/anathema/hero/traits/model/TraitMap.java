package net.sf.anathema.hero.traits.model;

public interface TraitMap {

  Trait getTrait(TraitType traitType);

  Trait[] getTraits(TraitType... traitType);

  Trait[] getAll();
}
