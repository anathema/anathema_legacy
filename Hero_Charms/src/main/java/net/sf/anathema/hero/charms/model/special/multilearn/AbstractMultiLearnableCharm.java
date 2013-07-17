package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.character.main.magic.charm.special.LearnRangeContext;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmVisitor;
import net.sf.anathema.hero.charms.model.special.multilearn.IMultiLearnableCharm;

public abstract class AbstractMultiLearnableCharm implements IMultiLearnableCharm {

  private final String charmId;

  public AbstractMultiLearnableCharm(String charmId) {
    this.charmId = charmId;
  }

  @Override
  public final String getCharmId() {
    return charmId;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitMultiLearnableCharm(this);
  }

  @Override
  public int getMinimumLearnCount(LearnRangeContext learnRangeContext) {
    return 1;
  }
}