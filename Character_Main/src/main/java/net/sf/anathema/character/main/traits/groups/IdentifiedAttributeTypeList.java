package net.sf.anathema.character.main.traits.groups;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AttributeGroupType;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.hero.concept.CasteType;

public class IdentifiedAttributeTypeList extends DefaultTraitTypeList implements IIdentifiedCasteTraitTypeList {

  private final AttributeGroupType groupType;

  public IdentifiedAttributeTypeList(AttributeGroupType groupType) {
    super(AttributeType.getAllFor(groupType));
    this.groupType = groupType;
  }

  @Override
  public AttributeGroupType getListId() {
    return groupType;
  }

  @Override
  public CasteType[] getTraitCasteTypes(TraitType type) {
    return new CasteType[0];
  }
}