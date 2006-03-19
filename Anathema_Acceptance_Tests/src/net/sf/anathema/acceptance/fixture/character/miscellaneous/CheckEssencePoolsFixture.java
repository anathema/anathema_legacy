package net.sf.anathema.acceptance.fixture.character.miscellaneous;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;

public class CheckEssencePoolsFixture extends AbstractCharacterColumnFixture {

  public String personalPool() {
    return getCharacterStatistics().getEssencePool().getPersonalPool();
  }

  public String peripheralPool() {
    return getCharacterStatistics().getEssencePool().getPeripheralPool();
  }
}