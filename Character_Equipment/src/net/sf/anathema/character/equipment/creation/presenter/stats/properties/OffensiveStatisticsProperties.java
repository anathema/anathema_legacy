package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.lib.resources.IResources;

public abstract class OffensiveStatisticsProperties extends EquipmentStatisticsProperties {

  public OffensiveStatisticsProperties(IResources resources) {
    super(resources);
  }

  public String getAccuracyLabel() {
    return "Accuracy:";
  }

  public String getSpeedLabel() {
    return "Speed:";
  }

  public String getRateLabel() {
    return "Rate:";
  }
}