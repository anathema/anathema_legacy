package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.hero.concept.ConceptChange;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.character.main.IIntegerDescription;
import net.sf.anathema.character.main.IntegerDescription;
import net.sf.anathema.hero.concept.CasteSelection;
import net.sf.anathema.hero.concept.DefaultCasteSelection;
import net.sf.anathema.hero.change.AnnounceChangeListener;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.util.Identifier;

public class DefaultHeroConcept implements HeroConcept, HeroModel {

  private final CasteSelection caste = new DefaultCasteSelection();
  private final IIntegerDescription age = new IntegerDescription(0);

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
    caste.addChangeListener(new AnnounceChangeListener(announcer, ConceptChange.FLAVOR_CASTE));
    age.addChangeListener(new AnnounceChangeListener(announcer, ConceptChange.FLAVOR_AGE));
  }

  @Override
  public CasteSelection getCaste() {
    return caste;
  }

  @Override
  public IIntegerDescription getAge() {
    return age;
  }
}