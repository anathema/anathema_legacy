package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.impl.model.IntegerDescription;
import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.main.model.concept.ConceptChange;
import net.sf.anathema.character.main.model.concept.HeroConcept;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.hero.change.AnnounceChangeListener;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.util.Identifier;

public class HeroConceptImpl implements HeroConcept, HeroModel {

  private final ITypedDescription<CasteType> caste = new TypedDescription<>(CasteType.NULL_CASTE_TYPE);
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
  public ITypedDescription<CasteType> getCaste() {
    return caste;
  }

  @Override
  public IIntegerDescription getAge() {
    return age;
  }
}