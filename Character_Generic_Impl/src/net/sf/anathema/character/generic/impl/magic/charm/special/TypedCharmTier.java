package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.character.GenericTraitProvider;
import net.sf.anathema.character.generic.traits.ITraitType;

import static net.sf.anathema.character.generic.traits.types.OtherTraitType.Essence;

public class TypedCharmTier implements CharmTier {
  private final int essenceRequirement;
  private final ITraitType traitType;
  private final int traitRequirement;

  public TypedCharmTier(int essenceRequirement, ITraitType traitType, int traitRequirement) {
    this.essenceRequirement = essenceRequirement;
    this.traitType = traitType;
    this.traitRequirement = traitRequirement;
  }

  @Override
  public boolean isLearnable(GenericTraitProvider traitProvider) {
    if (essenceRequirement > 0) {
      if (currentValueOf(traitProvider, Essence) < essenceRequirement) {
        return false;
      }
    }
    if (traitRequirement > 0) {
      if (currentValueOf(traitProvider, traitType) < traitRequirement) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int getRequirement(ITraitType type) {
    if (type == Essence) {
      return essenceRequirement;
    }
    if (type == traitType) {
      return traitRequirement;
    }
    return 0;
  }

  private int currentValueOf(GenericTraitProvider traitProvider, ITraitType type) {
    return traitProvider.getTrait(type).getCurrentValue();
  }
}