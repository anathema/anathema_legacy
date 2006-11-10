package net.sf.anathema.character.reporting.sheet.second.social;

import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractValueStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractSocialCombatsValueStatsGroup extends AbstractValueStatsGroup<ISocialCombatStats> {

  public AbstractSocialCombatsValueStatsGroup(IResources resources, String resourceKey) {
    super(resources, resourceKey);
  }

  @Override
  protected String getHeaderResourceBase() {
    return "Sheet.SocialCombat."; //$NON-NLS-1$
  }
}