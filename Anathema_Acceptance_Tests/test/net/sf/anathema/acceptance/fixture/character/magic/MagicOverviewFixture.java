package net.sf.anathema.acceptance.fixture.character.magic;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.impl.model.charm.LearningCharmGroupArbitrator;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;

public class MagicOverviewFixture extends AbstractCharacterColumnFixture {

  public int charmsLearned() {
    return getCharacterStatistics().getCharms().getLearnedCharms(true).length;
  }

  public int spellsLearned() {
    return getCharacterStatistics().getSpells().getLearnedSpells(true).length;
  }

  public int comboCount() {
    return getCharacterStatistics().getCombos().getCurrentCombos().length;
  }

  public boolean isCelestialMartialArtsTreeCompleted() {
    ILearningCharmGroup[] charms = getCharacterStatistics().getCharms().getCharmGroups(
        MartialArtsUtilities.MARTIAL_ARTS);
    LearningCharmGroupArbitrator arbitrator = new LearningCharmGroupArbitrator(
        getCharacterStatistics().getCharacterTemplate().getMagicTemplate().getCharmTemplate(),
        getCharacterStatistics().getCharacterContext());
    return arbitrator.isCelestialMartialArtsGroupCompleted(charms);
  }
}