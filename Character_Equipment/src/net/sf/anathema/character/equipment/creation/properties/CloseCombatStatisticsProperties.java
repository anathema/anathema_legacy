package net.sf.anathema.character.equipment.creation.properties;

import net.sf.anathema.lib.resources.IResources;

public class CloseCombatStatisticsProperties {

  private final IResources resources;

  public CloseCombatStatisticsProperties(IResources resources) {
    this.resources = resources;
  }

  public String getNameLabel() {
    return "Name:";
  }

  public String getSpeedLabel() {
    return "Speed:";
  }

  public String getPageDescription() {
    return "Description";
  }

  public String getDefaultMessage() {
    return "Default message";
  }
}