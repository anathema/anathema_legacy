package net.sf.anathema.character.main.testing.dummy.models;

import net.sf.anathema.character.main.model.concept.HeroConcept;
import net.sf.anathema.character.main.IIntegerDescription;
import net.sf.anathema.character.main.IntegerDescription;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.concept.model.concept.CasteSelection;
import net.sf.anathema.hero.concept.model.concept.DefaultCasteSelection;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.util.Identifier;

public class DummyHeroConcept implements HeroConcept {

  public CasteSelection caste = new DefaultCasteSelection();
  private IIntegerDescription age = new IntegerDescription(0);

  @Override
  public CasteSelection getCaste() {
    return caste;
  }

  @Override
  public IIntegerDescription getAge() {
    return age;
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
