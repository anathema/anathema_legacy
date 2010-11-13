package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.model.charm.OxBodyCategory;
import net.sf.anathema.character.model.health.IHealthLevelProvider;

public class OxBodyTechniqueHealthLevelProvider implements IHealthLevelProvider {

  private final OxBodyCategory[] categories;

  public OxBodyTechniqueHealthLevelProvider(OxBodyCategory[] categories) {
    this.categories = categories;
  }

  public int getHealthLevelTypeCount(HealthLevelType type) {
    int count = 0;
    for (OxBodyCategory category : categories) {
      count += category.getHealthLevelTypeCount(type) * category.getCurrentValue();
    }
    return count;
  }
}