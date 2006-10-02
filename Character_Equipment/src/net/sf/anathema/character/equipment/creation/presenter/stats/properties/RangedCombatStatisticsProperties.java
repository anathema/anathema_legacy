package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class RangedCombatStatisticsProperties extends OffensiveStatisticsProperties {

  public RangedCombatStatisticsProperties(IResources resources) {
    super(resources);
  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return new BasicMessage(getString("Equipment.Creation.RangedCombat.DefaultMessage")); //$NON-NLS-1$
  }

  @Override
  public String getPageDescription() {
    return getString("Equipment.Creation.RangedCombat.PageTitle"); //$NON-NLS-1$
  }

  public String getRangeLabel() {
    return getLabelString("Equipment.Stats.Long.Range"); //$NON-NLS-1$
  }

  @Override
  public String getDefaultName() {
    return getString("Equipment.Creation.RangedCombat.DefaultName"); //$NON-NLS-1$
  }
}