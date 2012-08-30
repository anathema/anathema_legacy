package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.character.GenericTraitProvider;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface CharmTier {
  boolean isLearnable(GenericTraitProvider traitProvider);

  int getRequirement(ITraitType type);
}
