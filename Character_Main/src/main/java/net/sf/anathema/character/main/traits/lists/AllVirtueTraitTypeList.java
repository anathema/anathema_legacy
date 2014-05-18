package net.sf.anathema.character.main.traits.lists;

import net.sf.anathema.character.main.traits.types.VirtueType;

public class AllVirtueTraitTypeList extends DefaultTraitTypeList {

  static AllVirtueTraitTypeList instance = new AllVirtueTraitTypeList();

  public static AllVirtueTraitTypeList getInstance() {
    return instance;
  }

  private AllVirtueTraitTypeList() {
    super(VirtueType.values());
  }
}