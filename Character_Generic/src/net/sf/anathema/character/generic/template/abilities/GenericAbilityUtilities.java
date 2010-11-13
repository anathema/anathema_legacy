package net.sf.anathema.character.generic.template.abilities;

import net.sf.anathema.character.generic.traits.types.AbilityType;

public class GenericAbilityUtilities {

  private GenericAbilityUtilities() {
    // Nothing to do
  }

  public static IGroupedTraitType[] createDefaultAbilityGroups() {
    return new IGroupedTraitType[] {
        new GroupedTraitType(AbilityType.Archery, AbilityGroupType.War.getId(), null),
        new GroupedTraitType(AbilityType.Athletics, AbilityGroupType.War.getId(), null),
        new GroupedTraitType(AbilityType.Awareness, AbilityGroupType.War.getId(), null),
        new GroupedTraitType(AbilityType.Brawl, AbilityGroupType.War.getId(), null),
        new GroupedTraitType(AbilityType.Dodge, AbilityGroupType.War.getId(), null),
        new GroupedTraitType(AbilityType.Endurance, AbilityGroupType.War.getId(), null),
        new GroupedTraitType(AbilityType.MartialArts, AbilityGroupType.War.getId(), null),
        new GroupedTraitType(AbilityType.Melee, AbilityGroupType.War.getId(), null),
        new GroupedTraitType(AbilityType.Resistance, AbilityGroupType.War.getId(), null),
        new GroupedTraitType(AbilityType.Thrown, AbilityGroupType.War.getId(), null),
        new GroupedTraitType(AbilityType.Craft, AbilityGroupType.Life.getId(), null),
        new GroupedTraitType(AbilityType.Larceny, AbilityGroupType.Life.getId(), null),
        new GroupedTraitType(AbilityType.Linguistics, AbilityGroupType.Life.getId(), null),
        new GroupedTraitType(AbilityType.Performance, AbilityGroupType.Life.getId(), null),
        new GroupedTraitType(AbilityType.Presence, AbilityGroupType.Life.getId(), null),
        new GroupedTraitType(AbilityType.Ride, AbilityGroupType.Life.getId(), null),
        new GroupedTraitType(AbilityType.Sail, AbilityGroupType.Life.getId(), null),
        new GroupedTraitType(AbilityType.Socialize, AbilityGroupType.Life.getId(), null),
        new GroupedTraitType(AbilityType.Stealth, AbilityGroupType.Life.getId(), null),
        new GroupedTraitType(AbilityType.Survival, AbilityGroupType.Life.getId(), null),
        new GroupedTraitType(AbilityType.Bureaucracy, AbilityGroupType.Wisdom.getId(), null),
        new GroupedTraitType(AbilityType.Investigation, AbilityGroupType.Wisdom.getId(), null),
        new GroupedTraitType(AbilityType.Lore, AbilityGroupType.Wisdom.getId(), null),
        new GroupedTraitType(AbilityType.Medicine, AbilityGroupType.Wisdom.getId(), null),
        new GroupedTraitType(AbilityType.Occult, AbilityGroupType.Wisdom.getId(), null) };
  }
}