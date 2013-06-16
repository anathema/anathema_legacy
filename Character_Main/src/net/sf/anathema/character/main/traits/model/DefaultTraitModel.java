package net.sf.anathema.character.main.traits.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.model.Hero;
import net.sf.anathema.lib.util.Identifier;

import java.util.Iterator;

import static java.util.Arrays.asList;

public class DefaultTraitModel extends DefaultTraitMap implements TraitMap, TraitModel {

  @Override
  public Iterator<Trait> iterator() {
    return asList(getAll()).iterator();
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(ChangeAnnouncer announcer, Hero hero) {
    //nothing to do
  }
}