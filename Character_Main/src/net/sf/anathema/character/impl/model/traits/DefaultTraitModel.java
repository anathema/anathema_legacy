package net.sf.anathema.character.impl.model.traits;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.AbstractTraitMap;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.traits.model.TraitMap;
import net.sf.anathema.character.main.traits.model.TraitModel;

import java.util.Iterator;

import static java.util.Arrays.asList;

public class DefaultTraitModel extends AbstractTraitMap implements TraitMap, TraitModel {

  @Override
  public Trait getTrait(TraitType traitType) {
    if (contains(traitType)) {
      return super.getTrait(traitType);
    }
    throw new UnsupportedOperationException("Unsupported trait type " + traitType);
  }

  @Override
  public Iterator<Trait> iterator() {
    return asList(getAllTraits()).iterator();
  }
}