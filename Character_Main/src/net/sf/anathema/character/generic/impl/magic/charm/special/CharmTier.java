package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.LearnRangeContext;
import net.sf.anathema.character.generic.traits.TraitType;

public interface CharmTier {
  boolean isLearnable(LearnRangeContext traitProvider);

  int getRequirement(TraitType type);
}