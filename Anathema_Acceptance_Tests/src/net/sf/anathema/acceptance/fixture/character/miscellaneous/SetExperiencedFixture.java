package net.sf.anathema.acceptance.fixture.character.miscellaneous;

import net.sf.anathema.acceptance.fixture.character.CharacterSummary;
import net.sf.anathema.character.model.ICharacter;
import fit.Fixture;
import fit.Parse;

public class SetExperiencedFixture extends Fixture {

  @Override
  public void doTable(Parse table) {
    @SuppressWarnings("unchecked")
    ICharacter character = new CharacterSummary(summary).getCharacter();
    character.getStatistics().setExperienced(true);
  }
}