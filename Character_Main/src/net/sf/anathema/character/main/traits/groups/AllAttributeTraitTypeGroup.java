package net.sf.anathema.character.main.traits.groups;

import net.sf.anathema.character.main.traits.types.AttributeType;

public class AllAttributeTraitTypeGroup extends TraitTypeGroup {

  static AllAttributeTraitTypeGroup instance = new AllAttributeTraitTypeGroup();

  public static AllAttributeTraitTypeGroup getInstance() {
    return instance;
  }

  private AllAttributeTraitTypeGroup() {
    super(AttributeType.values());
  }
}