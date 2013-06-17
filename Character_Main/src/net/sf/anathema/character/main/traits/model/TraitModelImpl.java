package net.sf.anathema.character.main.traits.model;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.model.CharacterModel;
import net.sf.anathema.character.main.model.Hero;
import net.sf.anathema.character.main.model.InitializationContext;
import net.sf.anathema.lib.util.Identifier;

import java.util.Iterator;

import static java.util.Arrays.asList;

public class TraitModelImpl extends DefaultTraitMap implements TraitMap, TraitModel, CharacterModel {

  @Override
  public Iterator<Trait> iterator() {
    return asList(getAll()).iterator();
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    //nothing to do
  }
}