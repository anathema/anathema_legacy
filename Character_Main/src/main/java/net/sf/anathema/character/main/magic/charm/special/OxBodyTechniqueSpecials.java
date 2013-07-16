package net.sf.anathema.character.main.magic.charm.special;

import net.sf.anathema.character.main.magic.charm.OxBodyCategory;
import net.sf.anathema.hero.health.IHealthLevelProvider;

public interface OxBodyTechniqueSpecials extends CharmSpecialsModel {
  OxBodyCategory[] getCategories();

  IHealthLevelProvider getHealthLevelProvider();

  OxBodyCategory getCategoryById(String name);
}