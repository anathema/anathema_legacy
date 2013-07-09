package net.sf.anathema.hero.magic;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharm;

public interface MagicCollection {
	
  int getLearnCount(String charmName);

  int getLearnCount(IMultiLearnableCharm multiLearnableCharm);

  boolean isLearned(Magic magic);
  
  void setLearnCount(String charmName, int newValue);

  void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue);
}