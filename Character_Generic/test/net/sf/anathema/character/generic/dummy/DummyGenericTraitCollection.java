package net.sf.anathema.character.generic.dummy;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.exception.NotYetImplementedException;

import java.util.HashMap;
import java.util.Map;

public class DummyGenericTraitCollection implements IGenericTraitCollection {

  private final Map<ITraitType, IGenericTrait> traits = new HashMap<>();

  @Override
  public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
    return (IFavorableGenericTrait) getTrait(type);
  }

  @Override
  public IGenericTrait getTrait(ITraitType type) {
    return traits.get(type);
  }

  @Override
  public IGenericTrait[] getTraits(ITraitType[] traitTypes) {
    throw new NotYetImplementedException();
  }

  public void setValue(ITraitType type, int value) {
    traits.put(type, new DummyGenericTrait(type, value));
  }

  @Override
  public boolean isFavoredOrCasteTrait(ITraitType type) {
    return getFavorableTrait(type).isCasteOrFavored();
  }
}