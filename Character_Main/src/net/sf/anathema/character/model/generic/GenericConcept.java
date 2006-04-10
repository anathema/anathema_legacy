package net.sf.anathema.character.model.generic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.INatureType;

public class GenericConcept implements IConcept {

  private final ICharacterConcept characterConcept;

  public GenericConcept(ICharacterConcept characterConcept) {
    this.characterConcept = characterConcept;
  }

  public String getConceptText() {
    return characterConcept.getConcept().getText();
  }

  public String getNatureName() {
    INatureType natureType = characterConcept.getNature().getType();
    return natureType == null ? null : natureType.getName();
  }

  public String getWillpowerCondition() {
    INatureType natureType = characterConcept.getNature().getType();
    return natureType == null ? null : natureType.getWillpowerCondition();
  }

  public ICasteType getCasteType() {
    return characterConcept.getCaste().getType();
  }
}