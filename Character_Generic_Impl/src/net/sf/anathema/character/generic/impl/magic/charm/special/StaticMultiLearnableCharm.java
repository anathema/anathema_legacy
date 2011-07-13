package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;

public class StaticMultiLearnableCharm extends AbstractMultiLearnableCharm {

  private final int learnCount;

  public StaticMultiLearnableCharm(String charmId, int learnCount) {
    super(charmId);
    this.learnCount = learnCount;
  }

  public int getAbsoluteLearnLimit() {
    return learnCount;
  }

  public int getMaximumLearnCount(IGenericTraitCollection traitCollection) {
    return learnCount;
  }
  
  public String toString()
  {
	  return "[" + getCharmId() + ";" + learnCount + "]";
  }
}