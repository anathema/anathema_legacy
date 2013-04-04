package net.sf.anathema.character.reporting.pdf.content.stats.social;

import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.character.reporting.pdf.content.stats.AbstractNameStatsGroup;
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
    return getResourceBase() + "AttackName"; //$NON-NLS-1$
  }

  @Override
  protected String getResourceBase() {
    return "Sheet.SocialCombat."; //$NON-NLS-1$
  }
}
