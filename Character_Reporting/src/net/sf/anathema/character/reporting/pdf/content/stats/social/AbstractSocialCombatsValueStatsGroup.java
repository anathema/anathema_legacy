package net.sf.anathema.character.reporting.pdf.content.stats.social;

import net.sf.anathema.character.main.social.ISocialCombatStats;
import net.sf.anathema.hero.sheet.pdf.content.stats.AbstractValueStatsGroup;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractSocialCombatsValueStatsGroup extends AbstractValueStatsGroup<ISocialCombatStats> {

  public AbstractSocialCombatsValueStatsGroup(Resources resources, String resourceKey) {
    super(resources, resourceKey);
  }

  @Override
  protected String getHeaderResourceBase() {
    return "Sheet.SocialCombat.";
  }
}
