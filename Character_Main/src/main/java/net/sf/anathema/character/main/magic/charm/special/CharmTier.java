package net.sf.anathema.character.main.magic.charm.special;

import net.sf.anathema.character.main.traits.TraitType;

public interface CharmTier {
  boolean isLearnable(LearnRangeContext traitProvider);

  int getRequirement(TraitType type);
}