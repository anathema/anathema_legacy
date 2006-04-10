package net.sf.anathema.acceptance.fixture.character.miscellaneous;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.INatureType;

public class CheckConceptFixture extends AbstractCharacterColumnFixture {

  public String concept() {
    return getCharacterConcept().getConcept().getText();
  }
  
  public String nature() {
    INatureType type = getCharacterConcept().getNature().getType();
    if (type == null) {
      return null;
    }
    return type.getId();
  }
  
  public String caste() {
    ICasteType caste = getCharacterConcept().getCaste().getType();
    if (caste == null) {
      return null;
    }
    return caste.getId();
  }

  protected final ICharacterConcept getCharacterConcept() {
    return getCharacterStatistics().getCharacterConcept();
  }
}