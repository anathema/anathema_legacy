package net.sf.anathema.character.main.testing.dummy.models;

import net.sf.anathema.character.main.IIntegerDescription;
import net.sf.anathema.character.main.IntegerDescription;
import net.sf.anathema.hero.concept.CasteCollection;
import net.sf.anathema.hero.concept.CasteSelection;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.lib.util.Identifier;

public class DummyHeroConcept implements HeroConcept {

  public NullCasteSelection caste = new NullCasteSelection();
  private IIntegerDescription age = new IntegerDescription(0);
  private NullCasteCollection casteCollection = new NullCasteCollection();

  @Override
  public CasteSelection getCaste() {
    return caste;
  }

  @Override
  public CasteCollection getCasteCollection() {
    return casteCollection;
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
  public void initialize(HeroEnvironment environment, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}
