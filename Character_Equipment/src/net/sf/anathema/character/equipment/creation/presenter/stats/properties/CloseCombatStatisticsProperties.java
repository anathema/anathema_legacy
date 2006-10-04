package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class CloseCombatStatisticsProperties extends OffensiveStatisticsProperties {

  private final IBasicMessage defaultMessage;

  public CloseCombatStatisticsProperties(IResources resources) {
    super(resources);
    this.defaultMessage= new BasicMessage(getString("Equipment.Creation.CloseCombat.DefaultMessage")); //$NON-NLS-1$
  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  @Override
  public String getPageDescription() {
    return getString("Equipment.Creation.CloseCombat.PageTitle"); //$NON-NLS-1$
  }

  public String getDefenseLabel() {
    return getLabelString("Equipment.Stats.Long.Defence"); //$NON-NLS-1$
  }

  @Override
  public String getDefaultName() {
    return getString("Equipment.Creation.CloseCombat.DefaultName"); //$NON-NLS-1$
  }
}