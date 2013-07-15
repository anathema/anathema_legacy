package net.sf.anathema.character.main.traits;

public interface ValuedTraitType {

  TraitType getType();

  int getCurrentValue();

  boolean isCasteOrFavored();
}