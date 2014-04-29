package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.framework.environment.Resources;

public class RangedCombatStatisticsProperties extends OffensiveStatisticsProperties {

  public RangedCombatStatisticsProperties(Resources resources) {
    super(resources);
  }

  public String getRangeLabel() {
    return getLabelString("Equipment.Stats.Long.Range");
  }
}