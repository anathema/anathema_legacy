package net.sf.anathema.character.main.concept.model;

import net.sf.anathema.character.change.AnnounceChangeListener;
import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.impl.model.IntegerDescription;
import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.Hero;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.lib.util.Identified;

public class CharacterConceptImpl implements CharacterConcept {

  private final ITypedDescription<ICasteType> caste = new TypedDescription<>(ICasteType.NULL_CASTE_TYPE);
  private final IIntegerDescription age = new IntegerDescription(0);

  @Override
  public ITypedDescription<ICasteType> getCaste() {
    return caste;
  }

  @Override
  public IIntegerDescription getAge() {
    return age;
  }

  @Override
  public Identified getId() {
    return ID;
  }

  @Override
  public void initialize(ChangeAnnouncer announcer, Hero hero) {
    caste.addChangeListener(new AnnounceChangeListener(announcer, ConceptChange.FLAVOR_CASTE));
    age.addChangeListener(new AnnounceChangeListener(announcer, ConceptChange.FLAVOR_AGE));
  }
}