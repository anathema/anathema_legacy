package net.sf.anathema.acceptance.fixture.character.magic;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;

public class ToggleCharmLearnedFixture extends AbstractCharacterRowEntryFixture {

  public String id;

  @Override
  public void enterRow() throws Exception {
    ICharmConfiguration charms = getCharacterStatistics().getCharms();
    ICharm charm = charms.getCharmById(id);
    ILearningCharmGroup learningCharmGroup = charms.getGroup(charm);
    learningCharmGroup.toggleLearned(charm);
  }
}