package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.MartialArtsCharmConfiguration;
import net.sf.anathema.character.generic.template.magic.MartialArtsRules;

public class DefaultMartialArtsRules implements MartialArtsRules {

  private boolean highLevelAtCreation = false;
  private final MartialArtsLevel level;

  public DefaultMartialArtsRules(MartialArtsLevel level) {
    this.level = level;
  }

  @Override
  public void setHighLevelAtCreation(boolean highLevelAtCreation) {
    this.highLevelAtCreation = highLevelAtCreation;
  }

  @Override
  public boolean isCharmAllowed(
      ICharm martialArtsCharm,
      MartialArtsCharmConfiguration charmConfiguration,
      boolean isExperienced) {
    int comparedLevel = MartialArtsUtilities.getLevel(martialArtsCharm).compareTo(level);
    if (comparedLevel <= 0) {
      return true;
    }
    if (comparedLevel == 1) {
      return isExperienced || highLevelAtCreation;
    }
    return false;
  }

  @Override
  public MartialArtsLevel getStandardLevel() {
    return level;
  }
}