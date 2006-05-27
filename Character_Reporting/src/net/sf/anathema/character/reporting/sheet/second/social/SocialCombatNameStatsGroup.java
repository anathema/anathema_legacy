package net.sf.anathema.character.reporting.sheet.second.social;

import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractNameStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public class SocialCombatNameStatsGroup extends AbstractNameStatsGroup<ISocialCombatStats> {

  public SocialCombatNameStatsGroup(IResources resources) {
    super(resources);
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