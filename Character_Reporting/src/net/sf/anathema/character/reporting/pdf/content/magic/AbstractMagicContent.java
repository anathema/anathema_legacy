package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicCostStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicDetailsStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicDurationStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicNameStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicSourceStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicTypeStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import java.util.List;

public abstract class AbstractMagicContent extends AbstractSubBoxContent {

  private IResources resources;

  public AbstractMagicContent(IResources resources) {
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

