package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.attributes.template.AttributeGroup;
import net.sf.anathema.character.attributes.template.AttributeTemplate;
import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.model.CharacterModel;
import net.sf.anathema.character.model.Hero;
import net.sf.anathema.character.trait.CurrentValue;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public class AttributesModel implements AttributesList, CharacterModel {

  public static final Identifier MODEL_ID = new Identifier("attributes");
  private AttributeTemplate template;

  public AttributesModel(AttributeTemplate template) {
    this.template = template;
  }

  @Override
  public Identified getId() {
    return MODEL_ID;
  }

  @Override
  public void initialize(ChangeAnnouncer announcer, Hero hero) {
    // nothing to do until now
  }

  public CurrentValue getCurrentValue(Identified traitId) {
    AttributeType type = getAttributeType(traitId);
    return null;
  }

  public int getTraitMaximum(IGenericCharacter character) {
    ICharacterTemplate characterTemplate = character.getTemplate();
    ITraitType traitType = characterTemplate.getAttributeGroups()[0].getTraitType();
    ITraitTemplate template = characterTemplate.getTraitTemplateCollection().getTraitTemplate(traitType);
    return template.getLimitation().getAbsoluteLimit(character);
  }

  public void iterate(AttributesIterator iterator) {
    for (AttributeGroup group : template.groups) {
      iterator.nextGroup(new Identifier(group.id));
      iterateGroup(iterator, group.attributes);
    }
  }

  private void iterateGroup(AttributesIterator iterator, List<String> attributeIds) {
    for (String traitId : attributeIds) {
      iterateTrait(iterator, new Identifier(traitId));
    }
  }

  private void iterateTrait(AttributesIterator iterator, Identified traitId) {
    int currentValue = getCurrentValue(traitId).getValue();
    iterator.nextTrait(traitId, currentValue);
  }

  private AttributeType getAttributeType(Identified traitId) {
    return AttributeType.valueOf(traitId.getId());
  }
}
