package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.itextpdf.text.pdf.PdfPTable;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicCostStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicDetailsStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicDurationStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicNameStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicSourceStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicTypeStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.stats.AbstractStatsTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class MagicTableEncoder extends AbstractStatsTableEncoder<IMagicStats, ReportSession> {

  private final IResources resources;
  private final boolean sectionHeaderLines;

  public MagicTableEncoder(IResources resources, boolean sectionHeaderLines) {
    super(sectionHeaderLines);
    this.resources = resources;
    this.sectionHeaderLines = sectionHeaderLines;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<IMagicStats>[] createStatsGroups(ReportSession session) {
    return new IStatsGroup[]{new MagicNameStatsGroup(resources), new MagicCostStatsGroup(resources), new MagicTypeStatsGroup(resources), new MagicDurationStatsGroup(resources), new MagicDetailsStatsGroup(resources), new MagicSourceStatsGroup(resources)};
  }

  @Override
  protected void encodeContent(SheetGraphics graphics, PdfPTable table, ReportSession session, Bounds bounds) {
    float heightLimit = bounds.height - 3;
    IStatsGroup<IMagicStats>[] groups = createStatsGroups(session);
    boolean encodeLine = true;
    String groupName = null;
    for (IMagicStats stats : session.getPrintMagic()) {
      String newGroupName = stats.getGroupName(resources);
      if (!ObjectUtilities.equals(groupName, newGroupName)) {
        groupName = newGroupName;
        encodeSectionLine(graphics, table, groupName);
        if (sectionHeaderLines) {
          encodeHeaderLine(graphics, table, groups);
        }
        encodeLine = table.getTotalHeight() < heightLimit;
        if (!encodeLine) {
          table.deleteLastRow();
          return;
        }
      }
      if (encodeLine) {
        encodeContentLine(graphics, table, groups, stats);
        encodeLine = table.getTotalHeight() < heightLimit;
        if (!encodeLine) {
          table.deleteLastRow();
          return;
        }
        session.removePrintMagic(stats);
      }
    }
    while (encodeLine) {
      encodeContentLine(graphics, table, groups, null);
      encodeLine = table.getTotalHeight() < heightLimit;
    }
    table.deleteLastRow();
  }
}
