package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_FAVORED;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_ABILITIES;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_SPECIALTY;

import java.util.Iterator;
import java.util.List;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.IFavorableModifiableTrait;
import net.sf.anathema.character.library.trait.persistence.AbstractCharacterPersister;
import net.sf.anathema.character.library.trait.specialties.ISpecialtyConfiguration;
import net.sf.anathema.character.library.trait.specialty.ISpecialtiesContainer;
import net.sf.anathema.character.library.trait.specialty.ISpecialty;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class AbilityConfigurationPersister extends AbstractCharacterPersister {

  public void save(Element parent, ICoreTraitConfiguration configuration) {
    Element abilitiesElement = parent.addElement(TAG_ABILITIES);
    for (IFavorableModifiableTrait ability : configuration.getAllAbilities()) {
      saveAbility(abilitiesElement, ability, configuration.getSpecialtyConfiguration());
    }
  }

  private void saveAbility(Element parent, IFavorableModifiableTrait ability, ISpecialtyConfiguration specialtyConfiguration) {
    ITraitType traitType = ability.getType();
    Element abilityElement = saveTrait(parent, traitType.getId(), ability);
    if (ability.getFavorization().isFavored()) {
      ElementUtilities.addAttribute(abilityElement, ATTRIB_FAVORED, ability.getFavorization().isFavored());
    }
    for (ISpecialty specialty : specialtyConfiguration.getSpecialtiesContainer(traitType).getSpecialties()) {
      Element specialtyElement = saveTrait(abilityElement, TAG_SPECIALTY, specialty);
      specialtyElement.addAttribute(ATTRIB_NAME, specialty.getName());
    }
  }

  public void load(Element parent, ICoreTraitConfiguration configuration) throws PersistenceException {
    Element abilitiesElement = ElementUtilities.getRequiredElement(parent, TAG_ABILITIES);
    List<Element> abilityElements = ElementUtilities.elements(abilitiesElement);
    for (Iterator<Element> allElementsIterator = abilityElements.iterator(); allElementsIterator.hasNext();) {
      loadAbility(allElementsIterator.next(), configuration);
    }
  }

  private void loadAbility(Element abilityElement, ICoreTraitConfiguration configuration) throws PersistenceException {
    AbilityType abilityType = AbilityType.valueOf(abilityElement.getName());
    IFavorableModifiableTrait ability = configuration.getFavorableTrait(abilityType);
    restoreTrait(abilityElement, ability);
    boolean favored = ElementUtilities.getBooleanAttribute(abilityElement, ATTRIB_FAVORED, false);
    ability.getFavorization().setFavored(favored);
    List<Element> specialtyElements = ElementUtilities.elements(abilityElement);
    ISpecialtyConfiguration specialtyConfiguration = configuration.getSpecialtyConfiguration();
    for (Iterator<Element> allSpecialties = specialtyElements.iterator(); allSpecialties.hasNext();) {
      Element specialtyElement = allSpecialties.next();
      String specialtyName = (specialtyElement).attributeValue(ATTRIB_NAME);
      ISpecialtiesContainer specialtiesContainer = specialtyConfiguration.getSpecialtiesContainer(abilityType);
      ISpecialty specialty = specialtiesContainer.addSpecialty(specialtyName);
      restoreTrait(specialtyElement, specialty);
    }
  }
}