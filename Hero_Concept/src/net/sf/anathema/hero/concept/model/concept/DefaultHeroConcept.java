package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.character.main.IIntegerDescription;
import net.sf.anathema.character.main.IntegerDescription;
import net.sf.anathema.hero.change.AnnounceChangeListener;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.concept.CasteCollection;
import net.sf.anathema.hero.concept.CasteSelection;
import net.sf.anathema.hero.concept.ConceptChange;
import net.sf.anathema.hero.concept.DefaultCasteModel;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.util.Identifier;

public class DefaultHeroConcept implements HeroConcept, HeroModel {

  private final IIntegerDescription age = new IntegerDescription(0);
  private DefaultCasteModel casteModel;

  public DefaultHeroConcept(DefaultCasteModel casteModel) {
    this.casteModel = casteModel;
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
    casteModel.getSelection().addChangeListener(new AnnounceChangeListener(announcer, ConceptChange.FLAVOR_CASTE));
    age.addChangeListener(new AnnounceChangeListener(announcer, ConceptChange.FLAVOR_AGE));
  }

  @Override
  public CasteSelection getCaste() {
    return casteModel.getSelection();
  }

  @Override
  public CasteCollection getCasteCollection() {
    return casteModel.getCollection();
  }

  @Override
  public IIntegerDescription getAge() {
    return age;
  }
}