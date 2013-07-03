package net.sf.anathema.character.generic.traits;

public interface ValuedTraitType {

  TraitType getType();

  int getCurrentValue();

  boolean isCasteOrFavored();
}