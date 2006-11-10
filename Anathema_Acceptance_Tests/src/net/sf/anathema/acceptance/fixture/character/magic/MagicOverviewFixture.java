package net.sf.anathema.acceptance.fixture.character.magic;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;

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
    return getCharacterStatistics().getCharms().isCelestialMartialArtsGroupCompleted();
  }
}