package net.sf.anathema.character.generic;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.main.model.concept.HeroConcept;

public class GenericConcept implements IConcept {

  private final HeroConcept heroConcept;

  public GenericConcept(HeroConcept heroConcept) {
    this.heroConcept = heroConcept;
  }

  @Override
  public CasteType getCasteType() {
    return heroConcept.getCaste().getType();
  }

  @Override
  public int getAge() {
    return heroConcept.getAge().getValue();
  }
}