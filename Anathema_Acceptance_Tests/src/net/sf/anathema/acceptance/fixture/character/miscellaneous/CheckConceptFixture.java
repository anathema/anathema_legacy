package net.sf.anathema.acceptance.fixture.character.miscellaneous;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.INature;
import net.sf.anathema.character.model.concept.INatureType;
import net.sf.anathema.lib.util.IIdentificate;

public class CheckConceptFixture extends AbstractCharacterColumnFixture {

  public String concept() {
    return getCharacter().getDescription().getConcept().getText();
  }

  public String nature() {
    INatureType type = ((INature) getCharacterConcept().getWillpowerRegainingConcept()).getDescription().getType();
    if (type == null) {
      return null;
    }
    return type.getId();
  }

  public String caste() {
    IIdentificate caste = getCharacterConcept().getCaste().getType();
    if (caste == null) {
      return null;
    }
    return caste.getId();
  }

  protected final ICharacterConcept getCharacterConcept() {
    return getCharacterStatistics().getCharacterConcept();
  }
}