package net.sf.anathema.acceptance.fixture.character;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.generic.template.ICharacterTemplate;

public class CreateCharacterFixture extends AbstractCharacterRowEntryFixture {

  public String characterType;
  public String subtemplate;
  public String edition;

  @Override
  public void enterRow() throws Exception {
    CharacterSummary characterSummary = getCharacterSummary();
    ICharacterTemplate template = characterSummary.createTemplate(characterType, subtemplate, edition);
    characterSummary.setCharacter(template);
  }
}