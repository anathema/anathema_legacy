package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_ATTRIBUTES;

import java.util.List;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class AttributeConfigurationPersister {

  private final TraitPersister persister = new TraitPersister();

  public void save(Element parent, ICoreTraitConfiguration traitConfiguration) {
    Element attributeElement = parent.addElement(TAG_ATTRIBUTES);
    for (IIdentifiedTraitTypeGroup typeGroup : traitConfiguration.getAttributeTypeGroups()) {
      saveAttributeGroup(attributeElement, traitConfiguration, typeGroup);
    }
  }

  private void saveAttributeGroup(
      Element parent,
      ICoreTraitConfiguration traitConfiguration,
      IIdentifiedTraitTypeGroup typeGroup) {
    Element groupElement = parent.addElement(typeGroup.getGroupId().getId());
    for (ITrait attribute : traitConfiguration.getTraits(typeGroup.getAllGroupTypes())) {
      persister.saveTrait(groupElement, attribute.getType().getId(), attribute);
    }
  }

  public void load(Element parent, ICoreTraitConfiguration configuration) throws PersistenceException {
    Element attributesElement = parent.element(TAG_ATTRIBUTES);
    for (Element groupElement : ElementUtilities.elements(attributesElement)) {
      loadAttributeGroup(groupElement, configuration);
    }
  }

  private void loadAttributeGroup(Element element, ICoreTraitConfiguration configuration) throws PersistenceException {
    List<Element> attributeElements = ElementUtilities.elements(element);
    for (int index = 0; index < attributeElements.size(); index++) {
      Element attributeElement = attributeElements.get(index);
      AttributeType attributeType = AttributeType.valueOf(attributeElement.getName());
      persister.restoreTrait(attributeElement, configuration.getTrait(attributeType));
    }
  }
}