package net.sf.anathema.character.main.magic.charm.special.charms;

import net.sf.anathema.character.main.magic.charm.special.LearnRangeContext;

public interface IMultiLearnableCharm extends ISpecialCharm {

  int getAbsoluteLearnLimit();
  
  int getMinimumLearnCount(LearnRangeContext learnRangeContext);

  int getMaximumLearnCount(LearnRangeContext learnRangeContext);
}