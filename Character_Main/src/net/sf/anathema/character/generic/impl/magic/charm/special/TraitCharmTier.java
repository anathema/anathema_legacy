package net.sf.anathema.character.generic.impl.magic.charm.special;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.magic.charms.special.LearnRangeContext;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.TraitType;

import java.util.List;

public class TraitCharmTier implements CharmTier {

  private final List<GenericTrait> requirements = Lists.newArrayList();

  public void addRequirement(GenericTrait requirement) {
    requirements.add(requirement);
  }

  @SuppressWarnings("RedundantIfStatement")
  @Override
  public boolean isLearnable(LearnRangeContext context) {
    for (GenericTrait requirement : requirements) {
      if (!context.isMinimumSatisfied(requirement)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int getRequirement(TraitType type) {
    for (GenericTrait requirement : requirements) {
      if (type == requirement.getType()) {
        return requirement.getCurrentValue();
      }
    }
    return 0;
  }
}