package net.sf.anathema.character.equipment.creation.properties;

import net.sf.anathema.lib.resources.IResources;

public class ShieldStatisticsProperties extends EquipmentStatisticsProperties {

  public ShieldStatisticsProperties(IResources resources) {
    super(resources);
  }
  
  public String getCloseCombatDvBonusLabel() {
    return "Close combat DV bonus:";
  }

  public String getRangedCombatDvBonusLabel() {
    return "Ranged combat DV bonus:";
  }
}