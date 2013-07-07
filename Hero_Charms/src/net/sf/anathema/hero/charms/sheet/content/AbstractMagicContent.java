package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.character.main.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.hero.charms.sheet.content.mnemonic.MagicMnemonic;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.hero.charms.sheet.content.stats.MagicCostStatsGroup;
import net.sf.anathema.hero.charms.sheet.content.stats.MagicDetailsStatsGroup;
import net.sf.anathema.hero.charms.sheet.content.stats.MagicDurationStatsGroup;
import net.sf.anathema.hero.charms.sheet.content.stats.MagicNameStatsGroup;
import net.sf.anathema.hero.charms.sheet.content.stats.MagicSourceStatsGroup;
import net.sf.anathema.hero.charms.sheet.content.stats.MagicTypeStatsGroup;
import net.sf.anathema.lib.resources.Resources;

import java.util.List;

public abstract class AbstractMagicContent extends AbstractSubBoxContent {

  private Resources resources;

  public AbstractMagicContent(Resources resources) {
    super(resources);
    this.resources = resources;
  }

  protected final void storeMnemonicIfNecessary(ReportSession session) {
    if (!knowsMnemonic(session)) {
      session.storeMnemonic(createMnemonic());
    }
  }

  protected abstract MagicMnemonic createMnemonic();

  protected abstract boolean knowsMnemonic(ReportSession session);

  protected abstract MagicMnemonic getMnemonic();

  public final List<IMagicStats> getPrintMagic() {
    return getMnemonic().getRemainingPrintMagic();
  }

  public final void markAsPrinted(IMagicStats stats) {
   getMnemonic().removePrintMagic(stats);
  }

  public final IStatsGroup<IMagicStats>[] createStatsGroups() {
    return new IStatsGroup[]{new MagicNameStatsGroup(resources), new MagicCostStatsGroup(resources),
            new MagicTypeStatsGroup(resources), new MagicDurationStatsGroup(resources),
            new MagicDetailsStatsGroup(resources), new MagicSourceStatsGroup(resources)};
  }

  public final boolean hasUnprintedCharms() {
    return !getPrintMagic().isEmpty();
  }

  public final String getGroupName(IMagicStats stats) {
    return  stats.getGroupName(resources);
  }
}

