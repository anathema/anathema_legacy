package net.sf.anathema.hero.traits.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.hero.HeroModel;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.hero.InitializationContext;
import net.sf.anathema.character.main.model.traits.DefaultTraitMap;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.lib.util.Identifier;

import java.util.Iterator;

import static java.util.Arrays.asList;

public class TraitModelImpl extends DefaultTraitMap implements TraitMap, TraitModel, HeroModel {

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }

  @Override
  public Iterator<Trait> iterator() {
    return asList(getAll()).iterator();
  }
}