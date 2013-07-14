package net.sf.anathema.hero.combat.sheet.social.stats;

import net.sf.anathema.hero.combat.model.social.ISocialCombatStats;
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
