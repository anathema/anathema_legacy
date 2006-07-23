package net.sf.anathema.character.equipment.creation.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.core.message.MessageType;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractOffensiveCombatStatisticsProperties {

  private final BasicMessage nameUndefinedMessage = new BasicMessage(
      "Please select a name for the equipment statstics or deselect the associated checkbox.",
      MessageType.ERROR);
  private final IResources resources;

  public AbstractOffensiveCombatStatisticsProperties(IResources resources) {
    this.resources = resources;
  }

  public String getAccuracyLabel() {
    return "Accuracy:";
  }

  public String getNameLabel() {
    return "Name:";
  }

  public String getSpeedLabel() {
    return "Speed:";
  }

  public String getRateLabel() {
    return "Rate:";
  }

  protected final IResources getResources() {
    return resources;
  }

  public IBasicMessage getUndefinedNameMessage() {
    return nameUndefinedMessage;
  }

  public abstract IBasicMessage getDefaultMessage();

  public abstract String getPageDescription();
}