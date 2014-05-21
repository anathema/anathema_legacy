package net.sf.anathema.hero.traits.model.lists;

import net.sf.anathema.hero.traits.model.types.AttributeType;

public class AllAttributeTraitTypeList extends DefaultTraitTypeList {

  static AllAttributeTraitTypeList instance = new AllAttributeTraitTypeList();

  public static AllAttributeTraitTypeList getInstance() {
    return instance;
  }

  private AllAttributeTraitTypeList() {
    super(AttributeType.values());
  }
}