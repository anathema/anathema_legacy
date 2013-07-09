package net.sf.anathema.character.main.magic.advance;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.IUpgradableCharmConfiguration;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.charms.CharmsModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.SpellModel;
import net.sf.anathema.hero.spells.SpellsModelFetcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class MagicCostEvaluator {

  private final CharmsModel charms;
  private final SpellModel spells;

  public MagicCostEvaluator(Hero hero) {
    this.charms = CharmsModelFetcher.fetch(hero);
    this.spells = SpellsModelFetcher.fetch(hero);
  }

  public List<Magic> compileCompleteMagicList() {
    List<Magic> completeList = new ArrayList<>();
    completeList.addAll(Arrays.asList(charms.getLearnedCharms(false)));
    completeList.addAll(Arrays.asList(spells.getLearnedSpells(false)));
    return completeList;
  }

  public int getLearnCount(Magic magic, Set<Magic> alreadyHandledMagic) {
    if (!(magic instanceof Charm)) {
      return 1;
    }
    Charm charm = (Charm) magic;
    int learnCount = handleSpecialCharm(charm);
    if (charms.isAlienCharm(charm)) {
      learnCount *= 2;
    }
    for (Charm mergedCharm : charm.getMergedCharms()) {
      if (alreadyHandledMagic.contains(mergedCharm) && !isSpecialCharm(charm)) {
        return 0;
      }
    }
    return learnCount;
  }

  private int handleSpecialCharm(Charm charm) {
    ISpecialCharmConfiguration specialCharmConfiguration = charms.getSpecialCharmConfiguration(charm);
    if (specialCharmConfiguration != null) {
      if (specialCharmConfiguration instanceof IUpgradableCharmConfiguration) {
        return 1;
      }
      return specialCharmConfiguration.getCreationLearnCount();
    }
    return 1;
  }

  private boolean isSpecialCharm(Charm charm) {
    return charms.getSpecialCharmConfiguration(charm) != null;
  }

  public int getAdditionalBonusPoints(Magic magic) {
    if (!(magic instanceof Charm)) {
      return 0;
    }
    ISpecialCharmConfiguration specialCharmConfiguration = charms.getSpecialCharmConfiguration((Charm) magic);
    if (specialCharmConfiguration instanceof IUpgradableCharmConfiguration) {
      return ((IUpgradableCharmConfiguration) specialCharmConfiguration).getUpgradeBPCost();
    }
    if (!(specialCharmConfiguration instanceof ISubeffectCharmConfiguration)) {
      return 0;
    }
    ISubeffectCharmConfiguration configuration = (ISubeffectCharmConfiguration) specialCharmConfiguration;
    int count = Math.max(0, (configuration.getCreationLearnedSubeffectCount() - 1));
    return (int) Math.ceil(count * configuration.getPointCostPerEffect());
  }
}
