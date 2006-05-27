package net.sf.anathema.character.reporting.sheet.second.social;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractStatsTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.statstable.IStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SocialCombatStatsTableEncoder extends AbstractStatsTableEncoder<ISocialCombatStats> {

  private final IResources resources;

  public SocialCombatStatsTableEncoder(IResources resources, BaseFont baseFont) {
    super(baseFont);
    this.resources = resources;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<ISocialCombatStats>[] createStatsGroups(IGenericCharacter character) {
    return new IStatsGroup[] {
        new SocialCombatNameStatsGroup(resources),
        new SocialSpeedStatsGroup(resources),
        new HonestyStatsGroup(resources),
        new DeceptionStatsGroup(resources),
        new SocialRateStatsGroup(resources)};
  }

  @Override
  protected int getLineCount() {
    return 3;
  }

  @Override
  protected ISocialCombatStats[] getPrintStats(IGenericCharacter character) {
    return new ISocialCombatStats[0];
  }

  @Override
  protected IGenericTrait getTrait(IGenericCharacter character, ISocialCombatStats equipment) {
    return null;
  }
}