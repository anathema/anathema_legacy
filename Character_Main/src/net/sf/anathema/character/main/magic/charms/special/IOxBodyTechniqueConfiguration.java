package net.sf.anathema.character.main.magic.charms.special;

import net.sf.anathema.hero.health.IHealthLevelProvider;
import net.sf.anathema.character.main.magic.charms.OxBodyCategory;

public interface IOxBodyTechniqueConfiguration extends ISpecialCharmConfiguration {
  OxBodyCategory[] getCategories();

  IHealthLevelProvider getHealthLevelProvider();

  OxBodyCategory getCategoryById(String name);
}