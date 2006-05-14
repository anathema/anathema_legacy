package net.sf.anathema.character.generic.framework.xml.rules.test;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;

public class DummyMagicCollection implements IMagicCollection {

  private final Map<IMultiLearnableCharm, Integer> counts = new HashMap<IMultiLearnableCharm, Integer>();

  public int getLearnCount(IMultiLearnableCharm multiLearnableCharm) {
    return counts.get(multiLearnableCharm);
  }

  public boolean isLearned(IMagic magic) {
    return false;
  }

  public void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue) {
    counts.put(multiLearnableCharm, newValue);
  }
}