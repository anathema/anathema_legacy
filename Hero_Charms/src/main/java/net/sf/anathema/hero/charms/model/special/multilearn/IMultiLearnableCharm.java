package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.character.main.magic.charm.special.LearnRangeContext;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

public interface IMultiLearnableCharm extends ISpecialCharm {

  int getAbsoluteLearnLimit();
  
  int getMinimumLearnCount(LearnRangeContext learnRangeContext);

  int getMaximumLearnCount(LearnRangeContext learnRangeContext);
}