package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.framework.environment.Resources;

public class CloseCombatStatisticsProperties extends OffensiveStatisticsProperties {

  public CloseCombatStatisticsProperties(Resources resources) {
    super(resources);
  }

  public String getDefenseLabel() {
    return getLabelString("Equipment.Stats.Long.Defence");
  }
}