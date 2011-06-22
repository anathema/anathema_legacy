package net.sf.anathema.acceptance.fixture.character;

import fit.Fixture;
import fit.Parse;

public class SetUnexperiencedFixture extends Fixture {

  @SuppressWarnings("unchecked")
  @Override
  public void doTable(Parse table) {
    new CharacterSummary(summary).getCharacter().getStatistics().setExperienced(false);
  }
}