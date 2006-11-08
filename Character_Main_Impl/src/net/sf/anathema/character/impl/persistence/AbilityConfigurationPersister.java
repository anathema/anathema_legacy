package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_FAVORED;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_ABILITIES;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_SPECIALTY;

import java.util.Iterator;
import java.util.List;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.library.trait.specialties.DefaultTraitReference;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.specialties.SubTraitReference;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.exception.NestedRuntimeException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class AbilityConfigurationPersister {

  public void save(Element parent, ICoreTraitConfiguration configuration) {
    Element abilitiesElement = parent.addElement(TAG_ABILITIES);
    for (IFavorableTrait ability : configuration.getAllAbilities()) {
      saveAbility(abilitiesElement, ability, configuration.getSpecialtyConfiguration());
    }
  }

  private final TraitPersister persister = new TraitPersister();

  private void saveAbility(
      Element parent,
      IFavorableTrait ability,
      final ISpecialtiesConfiguration specialtyConfiguration) {
    ITraitType traitType = ability.getType();
    final Element abilityElement = persister.saveTrait(parent, traitType.getId(), ability);
    if (ability.getFavorization().isFavored()) {
      ElementUtilities.addAttribute(abilityElement, ATTRIB_FAVORED, ability.getFavorization().isFavored());
    }
    ability.accept(new ITraitVisitor() {
      public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
        try {
          for (ISubTrait subTrait : visitedTrait.getSubTraits().getSubTraits()) {
            Element subTraitElement = getSubTraitElement(abilityElement, subTrait);
            if (subTraitElement == null) {
              throw new PersistenceException("No element found for SubTrait " + subTrait.getName()); //$NON-NLS-1$
            }
            SubTraitReference reference = new SubTraitReference(subTrait);
            saveSpecialties(specialtyConfiguration, subTraitElement, reference);
          }
        }
        catch (PersistenceException e) {
          throw new NestedRuntimeException(e);
        }
      }

      public void visitDefaultTrait(IDefaultTrait visitedTrait) {
        DefaultTraitReference reference = new DefaultTraitReference(visitedTrait);
        saveSpecialties(specialtyConfiguration, abilityElement, reference);
      }
    });
  }

  private Element getSubTraitElement(Element abilityElement, ISubTrait subTrait) throws PersistenceException {
    for (Element subTraitElement : ElementUtilities.elements(abilityElement, TraitPersister.TAG_SUB_TRAIT)) {
      if (subTrait.getName().equals(ElementUtilities.getRequiredText(subTraitElement, TraitPersister.TAG_TRAIT_NAME))) {
        return subTraitElement;
      }
    }
    return null;
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
    Element abilitiesElement = ElementUtilities.getRequiredElement(parent, TAG_ABILITIES);
    List<Element> abilityElements = ElementUtilities.elements(abilitiesElement);
    for (Iterator<Element> allElementsIterator = abilityElements.iterator(); allElementsIterator.hasNext();) {
      loadAbility(allElementsIterator.next(), configuration);
    }
  }

  private void loadAbility(final Element abilityElement, ICoreTraitConfiguration configuration)
      throws PersistenceException {
    AbilityType abilityType = AbilityType.valueOf(abilityElement.getName());
    IFavorableTrait ability = configuration.getFavorableTrait(abilityType);
    persister.restoreTrait(abilityElement, ability);
    boolean favored = ElementUtilities.getBooleanAttribute(abilityElement, ATTRIB_FAVORED, false);
    ability.getFavorization().setFavored(favored);
    final ISpecialtiesConfiguration specialtyConfiguration = configuration.getSpecialtyConfiguration();
    ability.accept(new ITraitVisitor() {
      public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
        try {
          for (ISubTrait subTrait : visitedTrait.getSubTraits().getSubTraits()) {
            Element subTraitElement = getSubTraitElement(abilityElement, subTrait);
            if (subTraitElement == null) {
              return;
            }
            loadSpecialties(subTraitElement, specialtyConfiguration, new SubTraitReference(subTrait));
          }

        }
        catch (PersistenceException e) {
          throw new NestedRuntimeException(e);
        }
      }

      public void visitDefaultTrait(IDefaultTrait visitedTrait) {
        try {
          loadSpecialties(abilityElement, specialtyConfiguration, new DefaultTraitReference(visitedTrait));
        }
        catch (PersistenceException e) {
          throw new NestedRuntimeException(e);
        }
      }
    });
  }

  private void loadSpecialties(
      final Element abilityElement,
      final ISpecialtiesConfiguration specialtyConfiguration,
      ITraitReference reference) throws PersistenceException {
    List<Element> specialtyElements = ElementUtilities.elements(abilityElement, TAG_SPECIALTY);
    for (Element specialtyElement : specialtyElements) {
      String specialtyName = (specialtyElement).attributeValue(ATTRIB_NAME);
      ISubTraitContainer specialtiesContainer = specialtyConfiguration.getSpecialtiesContainer(reference);
      ISubTrait specialty = specialtiesContainer.addSubTrait(specialtyName);
      persister.restoreTrait(specialtyElement, specialty);
    }
  }
}