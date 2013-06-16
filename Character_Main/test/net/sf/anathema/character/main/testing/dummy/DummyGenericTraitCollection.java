package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.lib.exception.NotYetImplementedException;

import java.util.HashMap;
import java.util.Map;

public class DummyGenericTraitCollection implements IGenericTraitCollection {

  private final Map<TraitType, GenericTrait> traits = new HashMap<>();

  @Override
  public GenericTrait getTrait(TraitType type) {
    return traits.get(type);
  }

  @Override
  public GenericTrait[] getTraits(TraitType[] traitTypes) {
    throw new NotYetImplementedException();
  }

  public void setValue(TraitType type, int value) {
    traits.put(type, new DummyGenericTrait(type, value));
  }

  @Override
  public boolean isFavoredOrCasteTrait(TraitType type) {
    return getTrait(type).isCasteOrFavored();
  }
}