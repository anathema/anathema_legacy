package net.sf.anathema.character.equipment.creation.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.core.message.MessageType;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentStatisticsProperties {

  private final BasicMessage nameUndefinedMessage = new BasicMessage(
      "Please select a name for the equipment statstics or deselect the associated checkbox.",
      MessageType.ERROR);
  private final IResources resources;

  public EquipmentStatisticsProperties(IResources resources) {
    this.resources = resources;
  }

  public String getNameLabel() {
    return "Name:";
  }

  protected final IResources getResources() {
    return resources;
  }

  public IBasicMessage getUndefinedNameMessage() {
    return nameUndefinedMessage;
  }
}