package net.sf.anathema.hero.traits.model;

public interface ValuedTraitType {

  TraitType getType();

  int getCurrentValue();

  boolean isCasteOrFavored();
}