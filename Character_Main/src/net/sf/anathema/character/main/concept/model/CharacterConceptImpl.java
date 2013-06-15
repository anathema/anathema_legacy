package net.sf.anathema.character.main.concept.model;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.impl.model.IntegerDescription;
import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;

public class CharacterConceptImpl implements CharacterConcept {

  private final ITypedDescription<ICasteType> caste = new TypedDescription<>(ICasteType.NULL_CASTE_TYPE);
  private IIntegerDescription age = new IntegerDescription(0);

  @Override
  public ITypedDescription<ICasteType> getCaste() {
    return caste;
  }

  @Override
  public IIntegerDescription getAge() {
    return age;
  }
}