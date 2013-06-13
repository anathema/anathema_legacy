package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.attributes.template.AttributeGroup;
import net.sf.anathema.character.attributes.template.AttributeTemplate;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.character.model.ModelGroup;
import net.sf.anathema.character.trait.CurrentValue;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

@CharacterModelAutoCollector
@Weight(weight = 0)
@ModelGroup(group = CharacterModelGroup.NaturalTraits)
public class AttributesModel implements AttributesList {

  private AttributeTemplate template;

  public AttributesModel(AttributeTemplate template) {
    this.template = template;
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
