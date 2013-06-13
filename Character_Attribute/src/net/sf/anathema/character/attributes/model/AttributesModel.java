package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class AttributesModel implements GroupedTraitList {

  private IGenericCharacter character;

  public AttributesModel(IGenericCharacter character) {
    this.character = character;
  }

  public int getTraitMax() {
    ITraitType traitType = getGroupedAttributeTypes()[0].getTraitType();
    ITraitTemplate template = getTraitTemplateCollection().getTraitTemplate(traitType);
    return template.getLimitation().getAbsoluteLimit(character);
  }

  @Override
  public void iterate(GroupedTraitsIterator iterator) {
    for (Identified groupId : getGroupedIds()) {
      iterator.nextGroup(groupId);
      iterateGroup(iterator, groupId);
    }
  }

  private void iterateGroup(GroupedTraitsIterator iterator, Identified groupId) {
    for (ITraitType traitType : getTraitTypes(groupId)) {
      iterateTrait(iterator, traitType);
    }
  }

  private void iterateTrait(GroupedTraitsIterator iterator, ITraitType traitType) {
    IGenericTrait trait = character.getTraitCollection().getTrait(traitType);
    int currentValue = trait.getCurrentValue();
    iterator.nextTrait(traitType, currentValue);
  }

  private ITraitTemplateCollection getTraitTemplateCollection() {
    return character.getTemplate().getTraitTemplateCollection();
  }

  private List<ITraitType> getTraitTypes(Identified groupId) {
    List<ITraitType> attributes = new ArrayList<>();
    for (GroupedTraitType groupedType : getGroupedAttributeTypes()) {
      if (groupedType.getGroupId().equals(groupId.getId())) {
        attributes.add(groupedType.getTraitType());
      }
    }
    return attributes;
  }

  private List<Identified> getGroupedIds() {
    List<Identified> groupIdList = new ArrayList<>();
    for (GroupedTraitType type : getGroupedAttributeTypes()) {
      Identifier groupId = new Identifier(type.getGroupId());
      if (!groupIdList.contains(groupId)) {
        groupIdList.add(groupId);
      }
    }
    return groupIdList;
  }

  private GroupedTraitType[] getGroupedAttributeTypes() {
    return character.getTemplate().getAttributeGroups();
  }
}
