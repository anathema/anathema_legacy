package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class CloseCombatStatisticsProperties extends OffensiveStatisticsProperties {

  private final BasicMessage defaultMessage = new BasicMessage("Please fill the close combat weapon statstics.");

  public CloseCombatStatisticsProperties(IResources resources) {
    super(resources);
  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  @Override
  public String getPageDescription() {
    return "Close Combat Weapon Stats";
  }

  public String getDefenseLabel() {
    return "Defense:";
  }

  @Override
  public String getDefaultName() {
    return "Close";
  }
}