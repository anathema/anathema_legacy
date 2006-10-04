package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class ArmourStatisticsProperties extends EquipmentStatisticsProperties {

  private final BasicMessage defaultMessage;

  public ArmourStatisticsProperties(IResources resources) {
    super(resources);
    this.defaultMessage = new BasicMessage(getString("Equipment.Creation.Armour.DefaultMessage")); //$NON-NLS-1$
  }

  public String getBashingSoakLabel() {
    return getLabelString("Equipment.Stats.Long.BashingSoak"); //$NON-NLS-1$
  }

  public String getLethalSoakLabel() {
    return getLabelString("Equipment.Stats.Long.LethalSoak"); //$NON-NLS-1$
  }

  public String getBashingHardnessLabel() {
    return getLabelString("Equipment.Stats.Long.BashingHardness"); //$NON-NLS-1$
  }

  public String getLethalHardnessLabel() {
    return getLabelString("Equipment.Stats.Long.LethalHardness"); //$NON-NLS-1$
  }

  public String getMobilityPenaltyLabel() {
    return getLabelString("Equipment.Stats.Long.MobilityPenalty"); //$NON-NLS-1$
  }

  public String getFatigueLabel() {
    return getLabelString("Equipment.Stats.Long.Fatigue"); //$NON-NLS-1$
  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  @Override
  public String getPageDescription() {
    return getString("Equipment.Creation.Armour.PageTitle"); //$NON-NLS-1$
  }

  @Override
  public String getDefaultName() {
    return getString("Equipment.Creation.Armour.DefaultName"); //$NON-NLS-1$
  }
}