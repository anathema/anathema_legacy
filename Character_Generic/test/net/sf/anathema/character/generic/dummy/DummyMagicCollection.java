package net.sf.anathema.character.generic.dummy;

import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;

import java.util.HashMap;
import java.util.Map;

public class DummyMagicCollection implements IMagicCollection {

  private final Map<IMultiLearnableCharm, Integer> counts = new HashMap<>();

  @Override
  public int getLearnCount(IMultiLearnableCharm multiLearnableCharm) {
    return counts.get(multiLearnableCharm);
  }

  @Override
  public boolean isLearned(IMagic magic) {
    return false;
  }

  @Override
  public void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue) {
    counts.put(multiLearnableCharm, newValue);
  }

	@Override
	public int getLearnCount(String charmName) {
		return 0;
	}

	@Override
	public void setLearnCount(String charmName, int newValue) {
		// TODO Auto-generated method stub
		
	}
}