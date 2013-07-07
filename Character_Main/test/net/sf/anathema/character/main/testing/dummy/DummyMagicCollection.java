package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.main.magic.model.magic.IMagic;
import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharm;
import net.sf.anathema.hero.magic.MagicCollection;

import java.util.HashMap;
import java.util.Map;

public class DummyMagicCollection implements MagicCollection {

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
      // Nothing to do
	}
}