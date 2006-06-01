package net.sf.anathema.character.reporting.sheet.second.social;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.social.InvestigationSocialAttack;
import net.sf.anathema.character.generic.impl.social.PerformanceSocialAttack;
import net.sf.anathema.character.generic.impl.social.PresenceSocialAttack;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractStatsTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.statstable.IStatsGroup;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;

public class SocialCombatStatsTableEncoder extends AbstractStatsTableEncoder<ISocialCombatStats> {

  private final IResources resources;

  public SocialCombatStatsTableEncoder(IResources resources, BaseFont baseFont) {
    super(baseFont);
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(IGenericCharacter character, Bounds bounds) {
    PdfPTable table = super.createTable(character, bounds);
    return table;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<ISocialCombatStats>[] createStatsGroups(IGenericCharacter character) {
    return new IStatsGroup[] {
        new SocialCombatNameStatsGroup(resources),
        new SocialSpeedStatsGroup(resources),
        new HonestyStatsGroup(resources),
        new DeceptionStatsGroup(resources),
        new SocialRateStatsGroup(resources) };
  }

  @Override
  protected boolean isLineValid(int line, Bounds bounds, PdfPTable table) {
    return line < getLineCount();
  }

  private int getLineCount() {
    return 3;
  }

  @Override
  protected ISocialCombatStats[] getPrintStats(IGenericCharacter character) {
    return new ISocialCombatStats[] {
        new PresenceSocialAttack(character),
        new PerformanceSocialAttack(character),
        new InvestigationSocialAttack(character) };
  }

  @Override
  protected IGenericTrait getTrait(IGenericCharacter character, ISocialCombatStats equipment) {
    return null;
  }
}