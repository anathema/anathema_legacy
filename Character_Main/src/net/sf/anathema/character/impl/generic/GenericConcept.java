package net.sf.anathema.character.impl.generic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.main.model.concept.CharacterConcept;

public class GenericConcept implements IConcept {

  private final CharacterConcept characterConcept;

  public GenericConcept(CharacterConcept characterConcept) {
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