package net.sf.anathema.character.generic.impl.traits.alternate;

import java.util.List;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;

public class TraitRequirementCollection {

  private final List<ITraitRequirement> requirements;
  private final int threshold;

  public TraitRequirementCollection(List<ITraitRequirement> requirements, int threshold) {
    this.requirements = requirements;
    this.threshold = threshold;
  }

  public boolean isStrictWithout(ITraitRequirement testRequirement, IGenericTraitCollection collection) {
    Preconditions.checkArgument(requirements.contains(testRequirement), "Foreign requirement"); //$NON-NLS-1$
    Preconditions.checkNotNull(testRequirement);
    int strictCount = 0;
    for (ITraitRequirement requirement : requirements) {
      if (requirement.equals(testRequirement)) {
        continue;
      }
      if (requirement.isCurrentlyStrict(collection)) {
        strictCount++;
      }
    }
    return strictCount >= threshold;
  }
}