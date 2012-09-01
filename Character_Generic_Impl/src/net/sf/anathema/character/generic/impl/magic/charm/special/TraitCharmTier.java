package net.sf.anathema.character.generic.impl.magic.charm.special;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.character.GenericTraitProvider;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

import java.util.List;

public class TraitCharmTier implements CharmTier {

  private final List<IGenericTrait> requirements = Lists.newArrayList();

  public void addRequirement(IGenericTrait requirement) {
    requirements.add(requirement);
  }

  @SuppressWarnings("RedundantIfStatement")
  @Override
  public boolean isLearnable(GenericTraitProvider traitProvider) {
    for (IGenericTrait requirement : requirements) {
      if (!isSatisfied(traitProvider, requirement)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int getRequirement(ITraitType type) {
    for (IGenericTrait requirement : requirements) {
      if (type == requirement.getType()) {
        return requirement.getCurrentValue();
      }
    }
    return 0;
  }

  private boolean isSatisfied(GenericTraitProvider traitProvider, IGenericTrait requirement) {
    if (requirement.getCurrentValue() > 0) {
      if (currentValueOf(traitProvider, requirement.getType()) < requirement.getCurrentValue()) {
        return false;
      }
    }
    return true;
  }

  private int currentValueOf(GenericTraitProvider traitProvider, ITraitType type) {
    return traitProvider.getTrait(type).getCurrentValue();
  }
}