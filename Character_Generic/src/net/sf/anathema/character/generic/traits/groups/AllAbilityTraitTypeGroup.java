package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.traits.types.AbilityType;

public class AllAbilityTraitTypeGroup extends TraitTypeGroup {

  private static final AllAbilityTraitTypeGroup instance = new AllAbilityTraitTypeGroup();

  public static AllAbilityTraitTypeGroup getInstance() {
    return instance;
  }

  private AllAbilityTraitTypeGroup() {
    super(AbilityType.values());
  }
}