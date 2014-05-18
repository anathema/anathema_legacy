package net.sf.anathema.character.main.traits.groups;

import net.sf.anathema.character.main.traits.types.AbilityType;

public class AllAbilityTraitTypeList extends DefaultTraitTypeList {

  private static final AllAbilityTraitTypeList instance = new AllAbilityTraitTypeList();

  public static AllAbilityTraitTypeList getInstance() {
    return instance;
  }

  private AllAbilityTraitTypeList() {
    super(AbilityType.values());
  }
}