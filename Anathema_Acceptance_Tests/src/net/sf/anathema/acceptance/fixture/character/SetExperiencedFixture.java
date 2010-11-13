package net.sf.anathema.acceptance.fixture.character;

import fit.Fixture;
import fit.Parse;

public class SetExperiencedFixture extends Fixture {

  @Override
  public void doTable(Parse table) {
    new CharacterSummary(summary).getCharacter().getStatistics().setExperienced(true);
  }
}