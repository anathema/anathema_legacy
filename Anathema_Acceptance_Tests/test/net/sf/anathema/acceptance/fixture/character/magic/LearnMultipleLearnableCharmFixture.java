package net.sf.anathema.acceptance.fixture.character.magic;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;

public class LearnMultipleLearnableCharmFixture extends AbstractCharacterRowEntryFixture {

  public String id;
  public int count;

  @Override
  public void enterRow() throws Exception {
    IMultiLearnableCharmConfiguration specialCharmConfiguration = (IMultiLearnableCharmConfiguration) getCharacterStatistics().getCharms()
        .getSpecialCharmConfiguration(id);
    specialCharmConfiguration.getCategory().setCurrentValue(count);
  }
}