package net.sf.anathema.hero.magic;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;

public interface MagicCollection {
	
  int getLearnCount(String charmName);

  int getLearnCount(IMultiLearnableCharm multiLearnableCharm);

  boolean isLearned(IMagic magic);
  
  void setLearnCount(String charmName, int newValue);

  void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue);
}