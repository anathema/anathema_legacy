package net.sf.anathema.acceptance.fixture.character.magic;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;

public class ToggleExtrinsicCharmLearnedFixture extends AbstractCharacterRowEntryFixture {

  public String charactertype;
  public String charmId;

  @Override
  public void enterRow() throws Exception {
    ICharmConfiguration charms = getCharacterStatistics().getCharms();
    ICharm charm = charms.getCharmById(charmId);
    ILearningCharmGroup learningCharmGroup = charms.getGroup(charm);
    learningCharmGroup.toggleLearned(charm);
  }
}