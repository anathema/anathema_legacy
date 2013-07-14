package net.sf.anathema.hero.combat.sheet.social.stats;

import net.sf.anathema.hero.combat.model.social.ISocialCombatStats;
import net.sf.anathema.hero.sheet.pdf.content.stats.AbstractNameStatsGroup;
import net.sf.anathema.lib.resources.Resources;

public class SocialCombatNameStatsGroup extends AbstractNameStatsGroup<ISocialCombatStats> {

  public SocialCombatNameStatsGroup(Resources resources) {
    super(resources);
  }

  @Override
  public Float[] getColumnWeights() {
    return new Float[]{3f};
  }

  @Override
  protected String getHeaderResourceKey() {
    return getResourceBase() + "AttackName";
  }

  @Override
  protected String getResourceBase() {
    return "Sheet.SocialCombat.";
  }
}
