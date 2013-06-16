package net.sf.anathema.character.impl.persistence;

import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.main.attributes.model.temporary.AttributeModel;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.List;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_FAVORED;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_ATTRIBUTES;

public class AttributeConfigurationPersister {

  private final TraitPersister persister = new TraitPersister();

  public void save(Element parent, AttributeModel attributeConfiguration) {
    Element attributesElement = parent.addElement(TAG_ATTRIBUTES);
    saveAttributes(attributeConfiguration, attributesElement);
  }

  private void saveAttributes(AttributeModel attributeConfiguration, Element attributesElement) {
    for (Trait attribute : attributeConfiguration.getAll()) {
      Element attributeElement = persister.saveTrait(attributesElement, attribute.getType().getId(), attribute);
        if (attribute.getFavorization().isFavored()) {
          ElementUtilities.addAttribute(attributeElement, ATTRIB_FAVORED, attribute.getFavorization().isFavored());
      }
    }
  }

  public void load(Element parent, AttributeModel configuration) throws PersistenceException {
    Element attributesElement = parent.element(TAG_ATTRIBUTES);
      loadAttributes(attributesElement, configuration);
  }

  private void loadAttributes(Element element, AttributeModel configuration) throws PersistenceException {
    List<Element> attributeElements = ElementUtilities.elements(element);
    for (Element attributeElement : attributeElements) {
      AttributeType attributeType = AttributeType.valueOf(attributeElement.getName());
      persister.restoreTrait(attributeElement, configuration.getTrait(attributeType));
      Trait attribute = configuration.getTrait(attributeType);
      if (ElementUtilities.getBooleanAttribute(attributeElement, ATTRIB_FAVORED, false)) {
        attribute.getFavorization().setFavored(true);
      }
    }
  }
}