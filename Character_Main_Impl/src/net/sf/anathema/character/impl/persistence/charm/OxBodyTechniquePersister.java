package net.sf.anathema.character.impl.persistence.charm;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CATEGORIES;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.model.charm.OxBodyCategory;
import net.sf.anathema.character.model.charm.special.IOxBodyTechniqueConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class OxBodyTechniquePersister implements ISpecialCharmPersister {
  private final TraitPersister traitPersister = new ExperiencedRestoringTraitPersister();

  public void saveConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration) {
    IOxBodyTechniqueConfiguration configuration = (IOxBodyTechniqueConfiguration) specialCharmConfiguration;
    Element categoriesElement = specialElement.addElement(TAG_CATEGORIES);
    for (OxBodyCategory category : configuration.getCategories()) {
      traitPersister.saveTrait(categoriesElement, category.getId(), category);
    }
  }

  public void loadConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration)
      throws PersistenceException {
    IOxBodyTechniqueConfiguration configuration = (IOxBodyTechniqueConfiguration) specialCharmConfiguration;
    Element categoriesElement = specialElement.element(TAG_CATEGORIES);
    if (categoriesElement != null) {
      for (Object categoryElementObject : categoriesElement.elements()) {
        Element categoryElement = (Element) categoryElementObject;
        OxBodyCategory category = configuration.getCategoryById(categoryElement.getName());
        traitPersister.restoreTrait(categoryElement, category);
      }
    }
  }
}