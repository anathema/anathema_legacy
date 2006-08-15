package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class ShieldStatisticsProperties extends EquipmentStatisticsProperties {

  private final BasicMessage defaultMessage = new BasicMessage("Please fill the shield statstics.");

  public ShieldStatisticsProperties(IResources resources) {
    super(resources);
  }
  
  public String getCloseCombatDvBonusLabel() {
    return "Close combat DV bonus:";
  }

  public String getRangedCombatDvBonusLabel() {
    return "Ranged combat DV bonus:";
  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  @Override
  public String getPageDescription() {
    return "Shield Stats";
  }
}