package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.Magic;
import net.sf.anathema.character.main.magic.spells.SpellImpl;
import net.sf.anathema.hero.charms.model.MagicLearner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class SpellsLearner implements MagicLearner {
  private SpellsModelImpl spellsModel;

  public SpellsLearner(SpellsModelImpl spellsModel) {
    this.spellsModel = spellsModel;
  }

  @Override
  public boolean handlesMagic(Magic magic) {
    return magic instanceof SpellImpl;
  }

  @Override
  public int getAdditionalBonusPoints(Magic magic) {
    return 0;
  }

  @Override
  public int getCreationLearnCount(Magic magic, Set<Magic> alreadyHandledMagic) {
    return 1;
  }

  @Override
  public Collection<? extends Magic> getLearnedMagic(boolean experienced) {
    return Arrays.asList(spellsModel.getLearnedSpells(experienced));
  }
}
