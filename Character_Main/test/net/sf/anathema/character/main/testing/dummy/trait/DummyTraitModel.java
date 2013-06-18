package net.sf.anathema.character.main.testing.dummy.trait;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.hero.HeroModel;
import net.sf.anathema.character.main.hero.InitializationContext;
import net.sf.anathema.character.main.model.traits.DefaultTraitMap;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identifier;

import java.util.Iterator;

public class DummyTraitModel extends DefaultTraitMap implements TraitModel, HeroModel {
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
}
