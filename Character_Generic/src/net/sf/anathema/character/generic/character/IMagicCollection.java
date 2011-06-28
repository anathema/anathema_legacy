package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;

public interface IMagicCollection {
	
  public int getLearnCount(String charmName);

  public int getLearnCount(IMultiLearnableCharm multiLearnableCharm);

  public boolean isLearned(IMagic magic);

  public void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue);
}