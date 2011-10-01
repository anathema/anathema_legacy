package net.sf.anathema.character.sidereal.template.trait;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.traits.alternate.ITraitRequirement;
import net.sf.anathema.character.generic.impl.traits.alternate.TraitRequirement;
import net.sf.anathema.character.generic.impl.traits.alternate.TraitRequirementCollection;
import net.sf.anathema.character.generic.impl.traits.caste.DynamicCasteMinimum;
import net.sf.anathema.character.generic.impl.traits.caste.ICasteTraitMinimum;
import net.sf.anathema.character.generic.impl.traits.range.AlternateRequirementTraitMinimum;
import net.sf.anathema.character.generic.traits.ITraitMinimum;
import net.sf.anathema.character.generic.traits.ITraitType;

public abstract class AbstractCasteTraitAlternative {

  private final List<ITraitRequirement> requirements = new ArrayList<ITraitRequirement>();
  private final TraitRequirementCollection requirementCollection;

  protected AbstractCasteTraitAlternative(int threshold) {
    requirementCollection = new TraitRequirementCollection(requirements, threshold);
  }

  protected final ICasteTraitMinimum[] createCasteTraitRange(ITraitType traittype) {
    ITraitRequirement requirement = createRequirement(traittype);
    requirements.add(requirement);
    ITraitMinimum alternateRange = new AlternateRequirementTraitMinimum(requirement, requirementCollection);
    return new ICasteTraitMinimum[] { new DynamicCasteMinimum(getCaste(), alternateRange) };
  }

  private ITraitRequirement createRequirement(ITraitType type) {
    return new TraitRequirement(getLowerFreeBound(), getLowerStrictBound(), type);
  }

  protected abstract int getLowerFreeBound();

  protected abstract int getLowerStrictBound();

  protected abstract ICasteType getCaste();
}