package net.sf.anathema.character.sidereal.template;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;
import net.sf.anathema.character.generic.template.magic.IMartialArtsRules;

public class SiderealMartialArtsRules implements IMartialArtsRules
{
	private static final int MAX_CREATION_SMA = 3;
	
  public void setHighLevelAtCreation(boolean highLevelAtCreation)
  {
  }

  public MartialArtsLevel getStandardLevel() {
    return MartialArtsLevel.Sidereal;
  }

  public boolean isCharmAllowed(
      ICharm martialArtsCharm,
      IGenericCharmConfiguration charmConfiguration,
      boolean isExperienced) {
    MartialArtsLevel level = MartialArtsUtilities.getLevel(martialArtsCharm);
    if (isExperienced ||
    	level != MartialArtsLevel.Sidereal ||
    	isLearned(martialArtsCharm, charmConfiguration))
    	return true;
    
    return countSMACharms(charmConfiguration) < MAX_CREATION_SMA;
  }
  
  private boolean isLearned(ICharm charm, IGenericCharmConfiguration config)
  {
	  for (ICharm match : config.getLearnedCharms())
		  if (charm == match)
			  return true;
	  return false;
  }
  
  private int countSMACharms(IGenericCharmConfiguration charmConfiguration)
  {
	  int smaCharms = 0;
	  for (ICharm charm : charmConfiguration.getLearnedCharms())
		  if (MartialArtsUtilities.getLevel(charm) == MartialArtsLevel.Sidereal)
			  smaCharms++;
	  return smaCharms;
  }
}