package net.sf.anathema.character.generic.impl.magic.charm.special;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.magic.charms.special.LearnRangeContext;
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
  public boolean isLearnable(LearnRangeContext context) {
    for (IGenericTrait requirement : requirements) {
      if (!context.isMinimumSatisfied(requirement)) {
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
}