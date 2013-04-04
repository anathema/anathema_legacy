package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.Resources;

public class CloseCombatStatisticsProperties extends OffensiveStatisticsProperties {

  private final IBasicMessage defaultMessage;

  public CloseCombatStatisticsProperties(Resources resources) {
    super(resources);
    this.defaultMessage = new BasicMessage(getString("Equipment.Creation.CloseCombat.DefaultMessage")); //$NON-NLS-1$
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