package net.sf.anathema.character.generic.traits;

public interface GenericTrait {

  TraitType getType();

  int getCurrentValue();

  boolean isCasteOrFavored();
}