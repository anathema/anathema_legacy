package net.sf.anathema.hero.charms.template.model;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities;

public class MartialArtsRulesImpl implements MartialArtsRules {

  private MartialArtsTto tto;

  public MartialArtsRulesImpl(MartialArtsTto tto) {
    this.tto = tto;
  }

  @Override
  public boolean isCharmAllowed(Charm martialArtsCharm, boolean isExperienced) {
    MartialArtsLevel charmLevel = MartialArtsUtilities.getLevel(martialArtsCharm);
    int comparedLevel = charmLevel.compareTo(getStandardLevel());
    if (comparedLevel <= 0) {
      return true;
    }
    if (comparedLevel == 1) {
      return isExperienced || tto.highLevelAtCreation;
    }
    return false;
  }

  @Override
  public MartialArtsLevel getStandardLevel() {
    return MartialArtsLevel.valueOf(tto.level);
  }
}