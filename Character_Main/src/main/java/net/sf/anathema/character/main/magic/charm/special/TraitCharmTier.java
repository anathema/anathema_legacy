package net.sf.anathema.character.main.magic.charm.special;

import com.google.common.collect.Lists;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.ValuedTraitType;

import java.util.List;

public class TraitCharmTier implements CharmTier {

  private final List<ValuedTraitType> requirements = Lists.newArrayList();

  public void addRequirement(ValuedTraitType requirement) {
    requirements.add(requirement);
  }

  @SuppressWarnings("RedundantIfStatement")
  @Override
  public boolean isLearnable(LearnRangeContext context) {
    for (ValuedTraitType requirement : requirements) {
      if (!context.isMinimumSatisfied(requirement)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int getRequirement(TraitType type) {
    for (ValuedTraitType requirement : requirements) {
      if (type == requirement.getType()) {
        return requirement.getCurrentValue();
      }
    }
    return 0;
  }
}