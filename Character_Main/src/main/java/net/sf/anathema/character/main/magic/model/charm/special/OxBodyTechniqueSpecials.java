package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.magic.model.charm.OxBodyCategory;
import net.sf.anathema.hero.health.IHealthLevelProvider;

public interface OxBodyTechniqueSpecials extends CharmSpecialsModel {
  OxBodyCategory[] getCategories();

  IHealthLevelProvider getHealthLevelProvider();

  OxBodyCategory getCategoryById(String name);
}