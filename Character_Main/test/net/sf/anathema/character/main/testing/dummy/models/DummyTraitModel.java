package net.sf.anathema.character.main.testing.dummy.models;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.traits.TraitValueStrategy;
import net.sf.anathema.character.main.traits.context.CreationTraitValueStrategy;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.traits.DefaultTraitMap;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identifier;

import java.util.Iterator;

public class DummyTraitModel extends DefaultTraitMap implements TraitModel, HeroModel {
  public TraitValueStrategy valueStrategy = new CreationTraitValueStrategy();

  @Override
  public Iterator<Trait> iterator() {
    throw new NotYetImplementedException();
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }

  @Override
  public TraitValueStrategy getValueStrategy() {
    return valueStrategy;
  }
}
