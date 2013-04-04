package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.Resources;

public class ArmourStatisticsProperties extends AbstractEquipmentStatisticsProperties {

  private final BasicMessage defaultMessage;

  public ArmourStatisticsProperties(Resources resources) {
    super(resources);
    this.defaultMessage = new BasicMessage(getString("Equipment.Creation.Armour.DefaultMessage"));
  }

  public String getBashingSoakLabel() {
    return getLabelString("Equipment.Stats.Long.BashingSoak");
  }

  public String getLethalSoakLabel() {
    return getLabelString("Equipment.Stats.Long.LethalSoak");
  }

  public String getBashingHardnessLabel() {
    return getLabelString("Equipment.Stats.Long.BashingHardness");
  }

  public String getLethalHardnessLabel() {
    return getLabelString("Equipment.Stats.Long.LethalHardness");
  }

  public String getMobilityPenaltyLabel() {
    return getLabelString("Equipment.Stats.Long.MobilityPenalty");
  }

  public String getFatigueLabel() {
    return getLabelString("Equipment.Stats.Long.Fatigue");
  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  @Override
  public String getPageDescription() {
    return getString("Equipment.Creation.Armour.PageTitle");
  }

  @Override
  public String getDefaultName() {
    return getString("Equipment.Creation.Armour.DefaultName");
  }

  public String getLinkSoakLabel() {
    return getString("Equipment.Creation.Armour.LinkSoak");
  }

  public String getAggravatedSoakLabel() {
    return getLabelString("Equipment.Stats.Long.AggravatedSoak");
  }
}