package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;
import net.sf.anathema.character.generic.template.magic.IMartialArtsRules;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;

public class DefaultMartialArtsRules implements IMartialArtsRules {

  private final boolean highLevelAtCreation;
  private final MartialArtsLevel level;

  public DefaultMartialArtsRules(MartialArtsLevel level, boolean highLevelAtCreation) {
    this.level = level;
    this.highLevelAtCreation = highLevelAtCreation;
  }

  public boolean isCharmAllowed(
      ICharm martialArtsCharm,
      IGenericCharmConfiguration charmConfiguration,
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

  public MartialArtsLevel getStandardLevel() {
    return level;
  }
}