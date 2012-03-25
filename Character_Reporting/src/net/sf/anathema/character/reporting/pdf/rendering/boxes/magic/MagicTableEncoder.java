package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.itextpdf.text.pdf.PdfPTable;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.magic.SimpleCharmContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.stats.AbstractStatsTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class MagicTableEncoder extends AbstractStatsTableEncoder<IMagicStats, ReportSession> {

  private final boolean sectionHeaderLines;

  public MagicTableEncoder(boolean sectionHeaderLines) {
    super(sectionHeaderLines);
    this.sectionHeaderLines = sectionHeaderLines;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<IMagicStats>[] createStatsGroups(ReportSession session) {
    return createContent(session).createStatsGroups();
  }

  @Override
  protected void encodeContent(SheetGraphics graphics, PdfPTable table, ReportSession session, Bounds bounds) {
    SimpleCharmContent content = createContent(session);
    float heightLimit = bounds.height - 3;
    IStatsGroup<IMagicStats>[] groups = createStatsGroups(session);
    boolean encodeLine = true;
    String groupName = null;
    for (IMagicStats stats : content.getPrintMagic()) {
      String newGroupName = content.getGroupName(stats);
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
        content.markAsPrinted(stats);
      }
    }
    while (encodeLine) {
      encodeContentLine(graphics, table, groups, null);
      encodeLine = table.getTotalHeight() < heightLimit;
    }
    table.deleteLastRow();
  }

  private SimpleCharmContent createContent(ReportSession session) {
    return session.createContent(SimpleCharmContent.class);
  }
}
