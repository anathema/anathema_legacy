package net.sf.anathema.hero.dummy.models;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitValueStrategy;
import net.sf.anathema.hero.traits.model.context.CreationTraitValueStrategy;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.traits.model.DefaultTraitMap;
import net.sf.anathema.hero.traits.model.TraitModel;
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
