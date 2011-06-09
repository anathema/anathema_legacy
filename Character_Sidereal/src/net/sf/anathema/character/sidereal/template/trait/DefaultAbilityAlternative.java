package net.sf.anathema.character.sidereal.template.trait;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.traits.alternate.AlternateRequirementTraitTemplate;
import net.sf.anathema.character.generic.impl.traits.alternate.ITraitRequirement;
import net.sf.anathema.character.generic.impl.traits.alternate.TraitRequirement;
import net.sf.anathema.character.generic.impl.traits.alternate.TraitRequirementCollection;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class DefaultAbilityAlternative {

  private final ITraitRequirement defaultThrown = new TraitRequirement(0, 1, AbilityType.Thrown);
  private final ITraitRequirement defaultMelee = new TraitRequirement(0, 1, AbilityType.Melee);
  private final ITraitRequirement defaultArchery = new TraitRequirement(0, 1, AbilityType.Archery);
  @SuppressWarnings("serial")
  private final List<ITraitRequirement> defaultAlternate = new ArrayList<ITraitRequirement>() {
    {
      add(defaultThrown);
      add(defaultMelee);
      add(defaultArchery);
    }
  };
  private final TraitRequirementCollection defaultRequirementCollection = new TraitRequirementCollection(
      defaultAlternate,
      1);

  public AlternateRequirementTraitTemplate createMeleeTemplate() {
    return new AlternateRequirementTraitTemplate(defaultMelee, defaultRequirementCollection, 0, 0);
  }

  public AlternateRequirementTraitTemplate createArcheryTemplate() {
    return new AlternateRequirementTraitTemplate(defaultArchery, defaultRequirementCollection, 1, 0);
  }

  public AlternateRequirementTraitTemplate createThrownTemplate() {
    return new AlternateRequirementTraitTemplate(defaultThrown, defaultRequirementCollection, 0, 0);
  }
}