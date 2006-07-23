package net.sf.anathema.character.equipment.creation.properties;

import net.disy.commons.core.message.IBasicMessage;
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

  public abstract IBasicMessage getDefaultMessage();

  public abstract String getPageDescription();
}