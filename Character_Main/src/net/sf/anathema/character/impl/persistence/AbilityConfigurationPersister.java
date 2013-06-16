package net.sf.anathema.character.impl.persistence;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.library.trait.specialties.DefaultTraitReference;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.List;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_FAVORED;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_ABILITIES;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_SPECIALTY;

public class AbilityConfigurationPersister {

  private final TraitPersister persister = new TraitPersister();

  public void save(Element parent, ICoreTraitConfiguration configuration) {
    Element abilitiesElement = parent.addElement(TAG_ABILITIES);
    for (AbilityType abilityType : AbilityType.values()) {
      IDefaultTrait ability = configuration.getTrait(abilityType);
      saveAbility(abilitiesElement, ability, configuration.getSpecialtyConfiguration());
    }
  }

  private void saveAbility(Element parent, IDefaultTrait ability, final ISpecialtiesConfiguration specialtyConfiguration) {
    ITraitType traitType = ability.getType();
    final Element abilityElement = persister.saveTrait(parent, traitType.getId(), ability);
    if (ability.getFavorization().isFavored()) {
      ElementUtilities.addAttribute(abilityElement, ATTRIB_FAVORED, ability.getFavorization().isFavored());
    }
    DefaultTraitReference reference = new DefaultTraitReference(ability);
    saveSpecialties(specialtyConfiguration, abilityElement, reference);
  }

  private void saveSpecialties(ISpecialtiesConfiguration specialtyConfiguration, Element abilityElement, ITraitReference reference) {
    for (ISubTrait specialty : specialtyConfiguration.getSpecialtiesContainer(reference).getSubTraits()) {
      Element specialtyElement = persister.saveTrait(abilityElement, TAG_SPECIALTY, specialty);
      specialtyElement.addAttribute(ATTRIB_NAME, specialty.getName());
    }
  }

  public void load(Element parent, ICoreTraitConfiguration configuration) throws PersistenceException {
    Element abilitiesElement = ElementUtilities.getRequiredElement(parent, TAG_ABILITIES);
    List<Element> abilityElements = ElementUtilities.elements(abilitiesElement);
    for (Element element : abilityElements) {
      loadAbility(element, configuration);
    }
  }

  private void loadAbility(final Element abilityElement, ICoreTraitConfiguration configuration) throws PersistenceException {
    AbilityType abilityType = AbilityType.valueOf(abilityElement.getName());
    IDefaultTrait ability = configuration.getTrait(abilityType);
    persister.restoreTrait(abilityElement, ability);
    boolean favored = ElementUtilities.getBooleanAttribute(abilityElement, ATTRIB_FAVORED, false);
    ability.getFavorization().setFavored(favored);
    final ISpecialtiesConfiguration specialtyConfiguration = configuration.getSpecialtyConfiguration();
    loadSpecialties(abilityElement, specialtyConfiguration, new DefaultTraitReference(ability));
  }

  private void loadSpecialties(Element abilityElement, ISpecialtiesConfiguration specialtyConfiguration, ITraitReference reference) throws
          PersistenceException {
    List<Element> specialtyElements = ElementUtilities.elements(abilityElement, TAG_SPECIALTY);
    for (Element specialtyElement : specialtyElements) {
      String specialtyName = specialtyElement.attributeValue(ATTRIB_NAME);
      ISubTraitContainer specialtiesContainer = specialtyConfiguration.getSpecialtiesContainer(reference);
      ISubTrait specialty = specialtiesContainer.addSubTrait(specialtyName);
      persister.restoreTrait(specialtyElement, specialty);
    }
  }
}