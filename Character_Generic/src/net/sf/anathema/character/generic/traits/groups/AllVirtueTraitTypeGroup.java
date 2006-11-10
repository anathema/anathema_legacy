package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.traits.types.VirtueType;

public class AllVirtueTraitTypeGroup extends TraitTypeGroup {

  static AllVirtueTraitTypeGroup instance = new AllVirtueTraitTypeGroup();

  public static AllVirtueTraitTypeGroup getInstance() {
    return instance;
  }

  private AllVirtueTraitTypeGroup() {
    super(VirtueType.values());
  }
}