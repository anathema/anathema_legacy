package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.model.charm.special.IUpgradableCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.SubEffectCharmSpecials;
import net.sf.anathema.character.main.magic.model.magic.Magic;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class CharmLearner implements MagicLearner {
  private CharmsModel charms;

  public CharmLearner(CharmsModel charms) {
    this.charms = charms;
  }

  @Override
  public boolean handlesMagic(Magic magic) {
    return magic instanceof Charm;

  }

  @Override
  public int getAdditionalBonusPoints(Magic magic) {
    CharmSpecialsModel specialCharmConfiguration = charms.getCharmSpecialsModel((Charm) magic);
    if (specialCharmConfiguration instanceof IUpgradableCharmConfiguration) {
      return ((IUpgradableCharmConfiguration) specialCharmConfiguration).getUpgradeBPCost();
    }
    if (!(specialCharmConfiguration instanceof SubEffectCharmSpecials)) {
      return 0;
    }
    SubEffectCharmSpecials configuration = (SubEffectCharmSpecials) specialCharmConfiguration;
    int count = Math.max(0, (configuration.getCreationLearnedSubEffectCount() - 1));
    return (int) Math.ceil(count * configuration.getPointCostPerEffect());
  }

  @Override
  public int getCreationLearnCount(Magic magic, Set<Magic> alreadyHandledMagic) {
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
    CharmSpecialsModel specialCharmConfiguration = charms.getCharmSpecialsModel(charm);
    if (specialCharmConfiguration != null) {
      if (specialCharmConfiguration instanceof IUpgradableCharmConfiguration) {
        return 1;
      }
      return specialCharmConfiguration.getCreationLearnCount();
    }
    return 1;
  }

  private boolean isSpecialCharm(Charm charm) {
    return charms.getCharmSpecialsModel(charm) != null;
  }

  @Override
  public Collection<? extends Magic> getLearnedMagic(boolean experienced) {
    return Arrays.asList(charms.getLearnedCharms(experienced));
  }
}
