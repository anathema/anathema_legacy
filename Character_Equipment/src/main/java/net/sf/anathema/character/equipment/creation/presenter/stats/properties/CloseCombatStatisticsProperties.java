package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.Resources;

public class CloseCombatStatisticsProperties extends OffensiveStatisticsProperties {

  private final IBasicMessage defaultMessage;

  public CloseCombatStatisticsProperties(Resources resources) {
    super(resources);
    this.defaultMessage = new BasicMessage(getString("Equipment.Creation.CloseCombat.DefaultMessage"));
  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  @Override
  public String getPageDescription() {
    return getString("Equipment.Creation.CloseCombat.PageTitle");
  }

  public String getDefenseLabel() {
    return getLabelString("Equipment.Stats.Long.Defence");
  }

  @Override
  public String getDefaultName() {
    return getString("Equipment.Creation.CloseCombat.DefaultName");
  }
}