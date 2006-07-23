package net.sf.anathema.character.equipment.creation.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class RangedCombatStatisticsProperties extends OffensiveStatisticsProperties {

  private final BasicMessage defaultMessage = new BasicMessage("Please fill the ranged weapon statstics.");

  public RangedCombatStatisticsProperties(IResources resources) {
    super(resources);
  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  @Override
  public String getPageDescription() {
    return "Ranged Weapon Stats";
  }

  public String getRangeLabel() {
    return "Range:";
  }
}