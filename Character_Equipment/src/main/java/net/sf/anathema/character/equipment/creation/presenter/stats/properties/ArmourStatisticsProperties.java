package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.framework.environment.Resources;

public class ArmourStatisticsProperties extends AbstractProperties {

  public ArmourStatisticsProperties(Resources resources) {
    super(resources);
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

  public String getLinkSoakLabel() {
    return getString("Equipment.Creation.Armour.LinkSoak");
  }

  public String getAggravatedSoakLabel() {
    return getLabelString("Equipment.Stats.Long.AggravatedSoak");
  }
}