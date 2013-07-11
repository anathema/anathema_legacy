package net.sf.anathema.character.main.persistence.charm;

import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.main.magic.model.charm.OxBodyCategory;
import net.sf.anathema.character.main.magic.model.charm.special.OxBodyTechniqueSpecials;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_CATEGORIES;

public class OxBodyTechniquePersister implements ISpecialCharmPersister {
  private final TraitPersister traitPersister = new ExperiencedRestoringTraitPersister();

  @Override
  public void saveConfiguration(Element specialElement, CharmSpecialsModel specialCharmConfiguration) {
    OxBodyTechniqueSpecials configuration = (OxBodyTechniqueSpecials) specialCharmConfiguration;
    Element categoriesElement = specialElement.addElement(TAG_CATEGORIES);
    for (OxBodyCategory category : configuration.getCategories()) {
      traitPersister.saveTrait(categoriesElement, category.getId(), category);
    }
  }

  @Override
  public void loadConfiguration(Element specialElement, CharmSpecialsModel specialCharmConfiguration) throws PersistenceException {
    OxBodyTechniqueSpecials configuration = (OxBodyTechniqueSpecials) specialCharmConfiguration;
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