package net.sf.anathema.character.main.concept.model;

import net.sf.anathema.character.change.AnnounceChangeListener;
import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.impl.model.IntegerDescription;
import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.main.model.HeroModel;
import net.sf.anathema.character.main.model.Hero;
import net.sf.anathema.character.main.model.InitializationContext;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.lib.util.Identifier;

public class CharacterConceptImpl implements CharacterConcept, HeroModel {

  private final ITypedDescription<ICasteType> caste = new TypedDescription<>(ICasteType.NULL_CASTE_TYPE);
  private final IIntegerDescription age = new IntegerDescription(0);

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    ChangeAnnouncer announcer = context.getChangeAnnouncer();
    caste.addChangeListener(new AnnounceChangeListener(announcer, ConceptChange.FLAVOR_CASTE));
    age.addChangeListener(new AnnounceChangeListener(announcer, ConceptChange.FLAVOR_AGE));
  }

  @Override
  public ITypedDescription<ICasteType> getCaste() {
    return caste;
  }

  @Override
  public IIntegerDescription getAge() {
    return age;
  }
}