package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.main.IGenericTraitCollection;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.exception.NotYetImplementedException;

import java.util.HashMap;
import java.util.Map;

public class DummyGenericTraitCollection implements IGenericTraitCollection {

  private final Map<TraitType, ValuedTraitType> traits = new HashMap<>();

  @Override
  public ValuedTraitType getTrait(TraitType type) {
    return traits.get(type);
  }

  @Override
  public ValuedTraitType[] getTraits(TraitType[] traitTypes) {
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