package net.sf.anathema.character.magic.charm;

import net.sf.anathema.character.magic.basic.attribute.MagicAttribute;

public interface ICharmLearnArbitrator {

  boolean isLearned(Charm charm);
  
  boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute, int threshold);
}