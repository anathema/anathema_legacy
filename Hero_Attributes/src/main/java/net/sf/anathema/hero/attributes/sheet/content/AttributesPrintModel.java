package net.sf.anathema.hero.attributes.sheet.content;

import net.sf.anathema.hero.traits.model.GroupedTraitType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.attributes.model.AttributesModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;

public class AttributesPrintModel implements AttributesList {

  private Hero hero;

  public AttributesPrintModel(Hero hero) {
    this.hero = hero;
  }

  public int getCurrentValue(Identifier traitId) {
    AttributeType type = AttributeType.valueOf(traitId.getId());
    return getAttributeModel().getTrait(type).getCurrentValue();
  }

  private AttributeModel getAttributeModel() {
    return AttributesModelFetcher.fetch(hero);
  }

  public int getTraitMaximum() {
    return getAttributeModel().getTraitMaximum();
  }

  @Override
  public void iterate(AttributesIterator iterator) {
    for (Identifier groupId : getGroupedIds()) {
      iterator.nextGroup(groupId);
      iterateGroup(iterator, groupId);
    }
  }

  private void iterateGroup(AttributesIterator iterator, Identifier groupId) {
    for (TraitType traitType : getTraitTypes(groupId)) {
      iterateTrait(iterator, traitType);
    }
  }

  private void iterateTrait(AttributesIterator iterator, TraitType traitType) {
     iterator.nextTrait(traitType);
  }

  private List<TraitType> getTraitTypes(Identifier groupId) {
    List<TraitType> attributes = new ArrayList<>();
    for (GroupedTraitType groupedType : getGroupedAttributeTypes()) {
      if (groupedType.getGroupId().equals(groupId.getId())) {
        attributes.add(groupedType.getTraitType());
      }
    }
    return attributes;
  }

  private List<Identifier> getGroupedIds() {
    List<Identifier> groupIdList = new ArrayList<>();
    for (GroupedTraitType type : getGroupedAttributeTypes()) {
      SimpleIdentifier groupId = new SimpleIdentifier(type.getGroupId());
      if (!groupIdList.contains(groupId)) {
        groupIdList.add(groupId);
      }
    }
    return groupIdList;
  }

  private GroupedTraitType[] getGroupedAttributeTypes() {
    return getAttributeModel().getAttributeGroups();
  }
}
