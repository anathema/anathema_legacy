package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.framework.environment.Resources;

public class RangedCombatStatisticsProperties extends OffensiveStatisticsProperties {

  private IBasicMessage defaultMessage;

  public RangedCombatStatisticsProperties(Resources resources) {
    super(resources);
    this.defaultMessage = new BasicMessage(getString("Equipment.Creation.RangedCombat.DefaultMessage"));
  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  @Override
  public String getPageDescription() {
    return getString("Equipment.Creation.RangedCombat.PageTitle");
  }

  public String getRangeLabel() {
    return getLabelString("Equipment.Stats.Long.Range");
  }

  @Override
  public String getDefaultName() {
    return getString("Equipment.Creation.RangedCombat.DefaultName");
  }
}