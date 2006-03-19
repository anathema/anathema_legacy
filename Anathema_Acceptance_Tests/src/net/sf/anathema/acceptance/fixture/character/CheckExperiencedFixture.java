package net.sf.anathema.acceptance.fixture.character;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;

public class CheckExperiencedFixture extends AbstractCharacterColumnFixture {

  public boolean isExperienced() {
    return getCharacterStatistics().isExperienced();
  }
}