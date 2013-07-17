package net.sf.anathema.hero.charms.model.special.oxbody;

import net.sf.anathema.character.main.magic.charm.OxBodyCategory;
import net.sf.anathema.character.main.magic.charm.special.CharmSpecialsModel;
import net.sf.anathema.hero.health.IHealthLevelProvider;

public interface OxBodyTechniqueSpecials extends CharmSpecialsModel {
  OxBodyCategory[] getCategories();

  IHealthLevelProvider getHealthLevelProvider();

  OxBodyCategory getCategoryById(String name);
}