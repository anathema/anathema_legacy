package net.sf.anathema.hero.charms.persistence.special.oxbody;

import net.sf.anathema.character.main.magic.model.charm.OxBodyCategory;
import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.model.charm.special.OxBodyTechniqueSpecials;
import net.sf.anathema.hero.charms.persistence.special.SpecialCharmPersister;
import net.sf.anathema.hero.charms.persistence.special.SpecialCharmPto;

public class OxBodyTechniquePersister implements SpecialCharmPersister {
  @Override
  public void saveCharmSpecials(CharmSpecialsModel charmSpecials, SpecialCharmPto charmPto) {
    OxBodyTechniqueSpecials configuration = (OxBodyTechniqueSpecials) charmSpecials;
    OxBodyTechniquePto oxBodyTechniquePto = createPto(configuration);
    charmPto.oxBodyTechnique = oxBodyTechniquePto;
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
}
