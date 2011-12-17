package net.sf.anathema.character.reporting.stats.social;

import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.character.reporting.stats.AbstractNameStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public class SocialCombatNameStatsGroup extends AbstractNameStatsGroup<ISocialCombatStats> {

  public SocialCombatNameStatsGroup(IResources resources) {
    super(resources);
  }

  @Override
  public Float[] getColumnWeights() {
    return new Float[] { new Float(3) };
  }

  @Override
  protected String getHeaderResourceKey() {
    return getResourceBase() + "AttackName"; //$NON-NLS-1$
  }

  @Override
  protected String getResourceBase() {
    return "Sheet.SocialCombat."; //$NON-NLS-1$
  }
}