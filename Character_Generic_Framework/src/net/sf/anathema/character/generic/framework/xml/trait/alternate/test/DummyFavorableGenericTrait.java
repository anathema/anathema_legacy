package net.sf.anathema.character.generic.framework.xml.trait.alternate.test;

import net.sf.anathema.character.generic.impl.testing.DummyGenericTrait;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class DummyFavorableGenericTrait extends DummyGenericTrait implements IFavorableGenericTrait {

  public DummyFavorableGenericTrait(ITraitType type, int currentValue) {
    super(type, currentValue);
  }

  public boolean isCasteOrFavored() {
    return false;
  }
}