package net.sf.anathema.dummy.character.trait;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class DummyGenericTraitCollection implements IGenericTraitCollection {

  private final Map<ITraitType, IGenericTrait> traits = new HashMap<ITraitType, IGenericTrait>();

  public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
    return (IFavorableGenericTrait) getTrait(type);
  }

  public IGenericTrait getTrait(ITraitType type) {
    return traits.get(type);
  }

  public void setValue(ITraitType type, int value) {
    traits.put(type, new DummyGenericTrait(type, value));
  }
}