package net.sf.anathema.character.attributes.sheet.content;

import net.sf.anathema.character.attributes.model.AttributesIterator;
import net.sf.anathema.character.attributes.model.AttributesList;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class TemporaryAttributesModel implements AttributesList {

  private IGenericCharacter character;

  public TemporaryAttributesModel(IGenericCharacter character) {
    this.character = character;
  }

  public int getCurrentValue(Identified traitId) {
    AttributeType type = AttributeType.valueOf(traitId.getId());
    return character.getTraitCollection().getTrait(type).getCurrentValue();
  }

  public int getTraitMaximum() {
    ITraitType traitType = getGroupedAttributeTypes()[0].getTraitType();
    ITraitTemplate template = getTraitTemplateCollection().getTraitTemplate(traitType);
    return template.getLimitation().getAbsoluteLimit(character);
  }

  @Override
  public void iterate(AttributesIterator iterator) {
    for (Identified groupId : getGroupedIds()) {
      iterator.nextGroup(groupId);
      iterateGroup(iterator, groupId);
    }
  }

  private void iterateGroup(AttributesIterator iterator, Identified groupId) {
    for (ITraitType traitType : getTraitTypes(groupId)) {
      iterateTrait(iterator, traitType);
    }
  }

  private void iterateTrait(AttributesIterator iterator, ITraitType traitType) {
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
