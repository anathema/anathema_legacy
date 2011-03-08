package net.sf.anathema.character.sidereal.template;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;
import net.sf.anathema.character.generic.template.magic.IMartialArtsRules;

public class RoninMartialArtsRules implements IMartialArtsRules {

  public void setHighLevelAtCreation(boolean highLevelAtCreation) {
  }

  public MartialArtsLevel getStandardLevel() {
    return MartialArtsLevel.Sidereal;
  }

  public boolean isCharmAllowed(
      ICharm martialArtsCharm,
      IGenericCharmConfiguration charmConfiguration,
      boolean isExperienced) {
    return isExperienced || 
    	MartialArtsUtilities.getLevel(martialArtsCharm) != MartialArtsLevel.Sidereal;
  }
}