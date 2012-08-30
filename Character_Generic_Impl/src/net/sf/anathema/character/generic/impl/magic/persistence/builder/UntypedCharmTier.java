package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.character.GenericTraitProvider;
import net.sf.anathema.character.generic.impl.magic.charm.special.CharmTier;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public class UntypedCharmTier implements CharmTier {
  private final int essenceRequirement;

  public UntypedCharmTier(int essence) {
    essenceRequirement = essence;
  }

  @Override
  public boolean isLearnable(GenericTraitProvider traitProvider) {
    if (essenceRequirement > 0) {
      if (traitProvider.getTrait(OtherTraitType.Essence).getCurrentValue() < essenceRequirement) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int getRequirement(ITraitType type) {
    if (type==OtherTraitType.Essence){
      return essenceRequirement;
    }
    return 0;
  }
}