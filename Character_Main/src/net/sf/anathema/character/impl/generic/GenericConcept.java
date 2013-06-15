package net.sf.anathema.character.impl.generic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.main.concept.model.ICharacterConcept;

public class GenericConcept implements IConcept {

  private final ICharacterConcept characterConcept;

  public GenericConcept(ICharacterConcept characterConcept) {
    this.characterConcept = characterConcept;
  }

  @Override
  public ICasteType getCasteType() {
    return characterConcept.getCaste().getType();
  }

  @Override
  public int getAge() {
    return characterConcept.getAge().getValue();
  }
}