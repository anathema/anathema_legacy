package net.sf.anathema.character.main.magic.charms.special;

public interface IMultiLearnableCharm extends ISpecialCharm {

  int getAbsoluteLearnLimit();
  
  int getMinimumLearnCount(LearnRangeContext learnRangeContext);

  int getMaximumLearnCount(LearnRangeContext learnRangeContext);
}