package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.core.message.MessageType;
import net.sf.anathema.lib.resources.IResources;

public abstract class EquipmentStatisticsProperties {

  private final BasicMessage nameUndefinedMessage = new BasicMessage(
      "Please enter a name for this set of statistics.",
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

  public abstract String getDefaultName();

  public abstract IBasicMessage getDefaultMessage();

  public abstract String getPageDescription();
}