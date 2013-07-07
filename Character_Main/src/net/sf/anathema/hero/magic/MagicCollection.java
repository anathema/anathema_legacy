package net.sf.anathema.hero.magic;

import net.sf.anathema.character.main.magic.IMagic;
import net.sf.anathema.character.main.magic.charms.special.IMultiLearnableCharm;

public interface MagicCollection {
	
  int getLearnCount(String charmName);

  int getLearnCount(IMultiLearnableCharm multiLearnableCharm);

  boolean isLearned(IMagic magic);
  
  void setLearnCount(String charmName, int newValue);

  void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue);
}