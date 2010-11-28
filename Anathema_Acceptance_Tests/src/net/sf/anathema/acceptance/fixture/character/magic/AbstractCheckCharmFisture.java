package net.sf.anathema.acceptance.fixture.character.magic;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.magic.ICharm;

public class AbstractCheckCharmFisture extends AbstractCharacterColumnFixture {

  public String id;

  protected final ICharm getCharm() {
    return getCharacterStatistics().getCharms().getCharmById(id);
  }
}