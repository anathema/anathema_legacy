package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.character.framework.IIntegerDescription;
import net.sf.anathema.character.framework.IntegerDescription;
import net.sf.anathema.hero.concept.CasteCollection;
import net.sf.anathema.hero.concept.CasteSelection;
import net.sf.anathema.hero.concept.ConceptChange;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.AnnounceChangeListener;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
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
  public void initialize(HeroEnvironment environment, Hero hero) {
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