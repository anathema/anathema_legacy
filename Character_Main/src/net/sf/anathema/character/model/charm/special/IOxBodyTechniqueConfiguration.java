package net.sf.anathema.character.model.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.model.charm.OxBodyCategory;
import net.sf.anathema.character.model.health.IHealthLevelProvider;

public interface IOxBodyTechniqueConfiguration extends ISpecialCharmConfiguration {
  public OxBodyCategory[] getCategories();

  public IHealthLevelProvider getHealthLevelProvider();

  public OxBodyCategory getCategoryById(String name);
}