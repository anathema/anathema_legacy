package net.sf.anathema.character.equipment.creation.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class CloseCombatStatisticsProperties {

  private final IResources resources;
  private final BasicMessage defaultMessage = new BasicMessage("Please fill the close range weapon statstics.");
  private final BasicMessage nameUndefinedMessage = new BasicMessage(
      "Please select a name for the close range weapon statstics.");

  public CloseCombatStatisticsProperties(IResources resources) {
    this.resources = resources;
  }

  public String getAccuracyLabel() {
    return "Accuracy:";
  }

  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  public String getNameLabel() {
    return "Name:";
  }

  public String getPageDescription() {
    return "Close Range Weapon Stats";
  }

  public String getSpeedLabel() {
    return "Speed:";
  }

  public IBasicMessage getUndefinedNameMessage() {
    return nameUndefinedMessage;
  }

  public String getDefenseLabel() {
    return "Defense:";
  }

  public String getRateLabel() {
    return "Rate:";
  }
}