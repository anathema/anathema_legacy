package net.sf.anathema.acceptance.fixture.character.miscellaneous;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.model.ICharacterDescription;

public class CheckDescriptionFixture extends AbstractCharacterColumnFixture {

  public String characterization() {
    return getCharacterDescription().getCharacterization().getText();
  }

  public String name() {
    return getCharacterDescription().getName().getText();
  }

  public String notes() {
    return getCharacterDescription().getNotes().getText();
  }

  public String periphrase() {
    return getCharacterDescription().getPeriphrase().getText();
  }

  public String physicalDescription() {
    return getCharacterDescription().getPhysicalDescription().getText();
  }

  protected final ICharacterDescription getCharacterDescription() {
    return getCharacter().getDescription();
  }
}