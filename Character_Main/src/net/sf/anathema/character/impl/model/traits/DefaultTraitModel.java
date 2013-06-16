package net.sf.anathema.character.impl.model.traits;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.traits.model.DefaultTraitMap;
import net.sf.anathema.character.main.traits.model.TraitMap;
import net.sf.anathema.character.main.traits.model.TraitModel;

import java.util.Iterator;

import static java.util.Arrays.asList;

public class DefaultTraitModel extends DefaultTraitMap implements TraitMap, TraitModel {

  @Override
  public Iterator<Trait> iterator() {
    return asList(getAll()).iterator();
  }
}