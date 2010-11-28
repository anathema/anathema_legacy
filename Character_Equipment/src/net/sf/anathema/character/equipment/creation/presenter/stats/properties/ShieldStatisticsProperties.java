package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class ShieldStatisticsProperties extends AbstractEquipmentStatisticsProperties {

  private final IBasicMessage defaultMessage;

  public ShieldStatisticsProperties(IResources resources) {
    super(resources);
    this.defaultMessage = new BasicMessage(getString("Equipment.Creation.Shield.DefaultMessage")); //$NON-NLS-1$
  }

  public String getCloseCombatDvBonusLabel() {
    return getLabelString("Equipment.Stats.Long.CloseDV"); //$NON-NLS-1$
  }

  public String getRangedCombatDvBonusLabel() {
    return getLabelString("Equipment.Stats.Long.RangedDV"); //$NON-NLS-1$
  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  @Override
  public String getPageDescription() {
    return getString("Equipment.Creation.Shield.PageTitle"); //$NON-NLS-1$
  }

  @Override
  public String getDefaultName() {
    return getString("Equipment.Creation.Shield.DefaultName"); //$NON-NLS-1$
  }

  public String getMobilityPenaltyLabel() {
    return getLabelString("Equipment.Stats.Long.MobilityPenalty"); //$NON-NLS-1$
  }

  public String getFatigueLabel() {
    return getLabelString("Equipment.Stats.Long.Fatigue"); //$NON-NLS-1$
  }
}