package net.sf.anathema.acceptance.fixture.character.magic;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.model.charm.special.IOxBodyTechniqueConfiguration;

public class LearnOxBodyTechniqueFixture extends AbstractCharacterRowEntryFixture {

  public String id;
  public int count;

  @Override
  public void enterRow() throws Exception {
    IOxBodyTechniqueConfiguration specialCharmConfiguration = (IOxBodyTechniqueConfiguration) getCharacterStatistics().getCharms()
        .getSpecialCharmConfiguration(id);
    specialCharmConfiguration.getCategories()[0].setCurrentValue(count);
  }
}