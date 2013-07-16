package net.sf.anathema.hero.charms.persistence.special.oxbody;

import net.sf.anathema.character.main.magic.charm.OxBodyCategory;
import net.sf.anathema.character.main.magic.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.charm.special.OxBodyTechniqueSpecials;
import net.sf.anathema.hero.charms.persistence.special.CharmSpecialsPto;
import net.sf.anathema.hero.charms.persistence.special.SpecialCharmPersister;

public class OxBodyTechniquePersister implements SpecialCharmPersister {
  @Override
  public void saveCharmSpecials(CharmSpecialsModel charmSpecials, CharmSpecialsPto specialsPto) {
    OxBodyTechniqueSpecials configuration = (OxBodyTechniqueSpecials) charmSpecials;
    OxBodyTechniquePto oxBodyTechniquePto = createPto(configuration);
    specialsPto.oxBodyTechnique = oxBodyTechniquePto;
  }

  private OxBodyTechniquePto createPto(OxBodyTechniqueSpecials configuration) {
    OxBodyTechniquePto oxBodyTechniquePto = new OxBodyTechniquePto();
    for (OxBodyCategory category : configuration.getCategories()) {
      OxBodyTechniqueCategoryPto pto = createCategoryPto(category);
      oxBodyTechniquePto.categories.add(pto);
    }
    return oxBodyTechniquePto;
  }

  private OxBodyTechniqueCategoryPto createCategoryPto(OxBodyCategory category) {
    OxBodyTechniqueCategoryPto pto = new OxBodyTechniqueCategoryPto();
    pto.id = category.getId();
    pto.creationValue = category.getCreationValue();
    if (category.getExperiencedValue() > 0) {
      pto.experienceValue = category.getExperiencedValue();
    }
    return pto;
  }

  @Override
  public void loadCharmSpecials(CharmSpecialsModel charmSpecials, CharmSpecialsPto specialsPto) {
    OxBodyTechniqueSpecials oxBodyTechniqueSpecials = (OxBodyTechniqueSpecials) charmSpecials;
    if (specialsPto.oxBodyTechnique == null) {
      return;
    }
    for (OxBodyTechniqueCategoryPto categoryPto : specialsPto.oxBodyTechnique.categories) {
      loadCategory(oxBodyTechniqueSpecials, categoryPto);
    }
  }

  private void loadCategory(OxBodyTechniqueSpecials oxBodyTechniqueSpecials, OxBodyTechniqueCategoryPto categoryPto) {
    OxBodyCategory category = oxBodyTechniqueSpecials.getCategoryById(categoryPto.id);
    category.setUncheckedCreationValue(categoryPto.creationValue);
    if (categoryPto.experienceValue != null) {
      category.setUncheckedExperiencedValue(categoryPto.experienceValue);
    }
  }
}
