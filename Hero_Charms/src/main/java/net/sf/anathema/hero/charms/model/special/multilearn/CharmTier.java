package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.character.main.traits.TraitType;

public interface CharmTier {
  boolean isLearnable(LearnRangeContext traitProvider);

  int getRequirement(TraitType type);
}