package net.sf.anathema.character.generic.traits;

public interface GenericTrait {

  ITraitType getType();

  int getCurrentValue();

  boolean isCasteOrFavored();
}