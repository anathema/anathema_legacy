package net.sf.anathema.character.main.testing.dummy.models;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitValueStrategy;
import net.sf.anathema.character.impl.model.context.trait.CreationTraitValueStrategy;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.model.traits.DefaultTraitMap;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.InitializationContext;
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
  public void initialize(InitializationContext context, Hero hero) {
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
