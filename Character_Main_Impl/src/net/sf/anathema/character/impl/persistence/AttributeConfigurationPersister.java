package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_FAVORED;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_ATTRIBUTES;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_SPECIALTY;

import java.util.List;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.library.trait.specialties.DefaultTraitReference;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
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
      IIdentifiedTraitTypeGroup typeGroup)
  {
    Element groupElement = parent.addElement(typeGroup.getGroupId().getId());
    final ISpecialtiesConfiguration config = traitConfiguration.getSpecialtyConfiguration();
    for (ITrait attribute : traitConfiguration.getTraits(typeGroup.getAllGroupTypes())) {
    	{
    		Element attributeElement = persister.saveTrait(groupElement, attribute.getType().getId(), attribute);
    		if (attribute instanceof DefaultTrait)
    		{
                DefaultTrait attributeTrait = (DefaultTrait) attribute;
    			if (attributeTrait.getFavorization().isFavored()) {
      		      ElementUtilities.addAttribute(attributeElement, ATTRIB_FAVORED,
      		    		  attributeTrait.getFavorization().isFavored());
      		    }
		        DefaultTraitReference reference = new DefaultTraitReference(attributeTrait);
		        saveSpecialties(config, attributeElement, reference);
    		}
    	}
    }
  }
  
  private void saveSpecialties(
	      final ISpecialtiesConfiguration specialtyConfiguration,
	      final Element abilityElement,
	      ITraitReference reference) {
	    for (ISubTrait specialty : specialtyConfiguration.getSpecialtiesContainer(reference).getSubTraits()) {
	      Element specialtyElement = persister.saveTrait(abilityElement, TAG_SPECIALTY, specialty);
	      specialtyElement.addAttribute(ATTRIB_NAME, specialty.getName());
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
    final ISpecialtiesConfiguration specialtyConfiguration = configuration.getSpecialtyConfiguration();
      for (Element attributeElement : attributeElements) {
          AttributeType attributeType = AttributeType.valueOf(attributeElement.getName());
          persister.restoreTrait(attributeElement, configuration.getTrait(attributeType));
          IFavorableTrait attribute = configuration.getFavorableTrait(attributeType);
          if (ElementUtilities.getBooleanAttribute(attributeElement, ATTRIB_FAVORED, false))
              attribute.getFavorization().setFavored(true);
          loadSpecialties(attributeElement, specialtyConfiguration, new DefaultTraitReference(attribute));
      }
  }
  
  private void loadSpecialties(
	      final Element abilityElement,
	      final ISpecialtiesConfiguration specialtyConfiguration,
	      ITraitReference reference) throws PersistenceException {
	    List<Element> specialtyElements = ElementUtilities.elements(abilityElement, TAG_SPECIALTY);
	    for (Element specialtyElement : specialtyElements) {
	      String specialtyName = specialtyElement.attributeValue(ATTRIB_NAME);
	      ISubTraitContainer specialtiesContainer = specialtyConfiguration.getSpecialtiesContainer(reference);
	      ISubTrait specialty = specialtiesContainer.addSubTrait(specialtyName);
	      persister.restoreTrait(specialtyElement, specialty);
	    }
	  }
}