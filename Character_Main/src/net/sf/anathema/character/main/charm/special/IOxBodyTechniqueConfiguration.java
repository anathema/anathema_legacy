package net.sf.anathema.character.main.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.model.health.IHealthLevelProvider;
import net.sf.anathema.character.main.charm.OxBodyCategory;

public interface IOxBodyTechniqueConfiguration extends ISpecialCharmConfiguration {
  OxBodyCategory[] getCategories();

  IHealthLevelProvider getHealthLevelProvider();

  OxBodyCategory getCategoryById(String name);
}