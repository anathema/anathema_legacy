package net.sf.anathema.character.reporting.sheet.second;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.social.InvestigationSocialAttack;
import net.sf.anathema.character.generic.impl.social.PerformanceSocialAttack;
import net.sf.anathema.character.generic.impl.social.PresenceSocialAttack;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.character.reporting.common.stats.IStatsGroup;
import net.sf.anathema.character.reporting.common.stats.social.DeceptionStatsGroup;
import net.sf.anathema.character.reporting.common.stats.social.HonestyStatsGroup;
import net.sf.anathema.character.reporting.common.stats.social.SocialCombatNameStatsGroup;
import net.sf.anathema.character.reporting.common.stats.social.SocialRateStatsGroup;
import net.sf.anathema.character.reporting.common.stats.social.SocialSpeedStatsGroup;
import net.sf.anathema.character.reporting.sheet.util.AbstractFixedLineStatsTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SocialCombatStatsTableEncoder extends AbstractFixedLineStatsTableEncoder<ISocialCombatStats> {

  private final IResources resources;

  public SocialCombatStatsTableEncoder(IResources resources, BaseFont baseFont) {
    super(baseFont);
    this.resources = resources;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<ISocialCombatStats>[] createStatsGroups(IGenericCharacter character) {
    return new IStatsGroup[]{new SocialCombatNameStatsGroup(resources), new SocialSpeedStatsGroup(resources), new HonestyStatsGroup(resources),
                             new DeceptionStatsGroup(resources), new SocialRateStatsGroup(resources)};
  }

  @Override
  protected int getLineCount() {
    return 3;
  }

  @Override
  protected ISocialCombatStats[] getPrintStats(IGenericCharacter character) {
    IGenericTraitCollection traitCollection = character.getTraitCollection();
    return new ISocialCombatStats[]{new PresenceSocialAttack(traitCollection), new PerformanceSocialAttack(traitCollection),
                                    new InvestigationSocialAttack(traitCollection)};
  }
}
