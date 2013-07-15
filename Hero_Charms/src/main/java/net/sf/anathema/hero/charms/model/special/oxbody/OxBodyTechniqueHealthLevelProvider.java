package net.sf.anathema.hero.charms.model.special.oxbody;

import net.sf.anathema.character.main.magic.model.charm.OxBodyCategory;
import net.sf.anathema.hero.health.IHealthLevelProvider;
import net.sf.anathema.health.HealthLevelType;

public class OxBodyTechniqueHealthLevelProvider implements IHealthLevelProvider {

  private final OxBodyCategory[] categories;

  public OxBodyTechniqueHealthLevelProvider(OxBodyCategory[] categories) {
    this.categories = categories;
  }

  @Override
  public int getHealthLevelTypeCount(HealthLevelType type) {
    int count = 0;
    for (OxBodyCategory category : categories) {
      count += category.getHealthLevelTypeCount(type) * category.getCurrentValue();
    }
    return count;
  }
}