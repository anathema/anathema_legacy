package net.sf.anathema.hero.traits.model.lists;

import net.sf.anathema.hero.traits.model.types.VirtueType;

public class AllVirtueTraitTypeList extends DefaultTraitTypeList {

  static AllVirtueTraitTypeList instance = new AllVirtueTraitTypeList();

  public static AllVirtueTraitTypeList getInstance() {
    return instance;
  }

  private AllVirtueTraitTypeList() {
    super(VirtueType.values());
  }
}