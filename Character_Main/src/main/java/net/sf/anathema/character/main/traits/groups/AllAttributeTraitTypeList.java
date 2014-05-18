package net.sf.anathema.character.main.traits.groups;

import net.sf.anathema.character.main.traits.types.AttributeType;

public class AllAttributeTraitTypeList extends DefaultTraitTypeList {

  static AllAttributeTraitTypeList instance = new AllAttributeTraitTypeList();

  public static AllAttributeTraitTypeList getInstance() {
    return instance;
  }

  private AllAttributeTraitTypeList() {
    super(AttributeType.values());
  }
}