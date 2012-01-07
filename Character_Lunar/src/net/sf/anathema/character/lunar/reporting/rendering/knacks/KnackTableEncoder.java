package net.sf.anathema.character.lunar.reporting.rendering.knacks;

import com.lowagie.text.pdf.PdfPTable;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.lunar.reporting.content.knacks.KnackContent;
import net.sf.anathema.character.lunar.reporting.content.stats.knacks.IKnackStats;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.stats.AbstractStatsTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class KnackTableEncoder extends AbstractStatsTableEncoder<IKnackStats, KnackContent> {

  @Override
  protected IStatsGroup<IKnackStats>[] createStatsGroups(KnackContent knackContent) {
    return knackContent.createStatsGroups();
  }

  @Override
  protected void encodeContent(SheetGraphics graphics, PdfPTable table, KnackContent knackContent, Bounds bounds) {
    IStatsGroup<IKnackStats>[] groups = knackContent.createStatsGroups();
    float heightLimit = bounds.height - 3;
    boolean encodeLine = true;
    String groupName = null;
    for (IKnackStats stats : knackContent.createPrintKnacks()) {
      String newGroupName = knackContent.getGroupLabel(stats);
      if (!ObjectUtilities.equals(groupName, newGroupName)) {
        groupName = newGroupName;
        encodeSectionLine(graphics, table, groupName);
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
      }
    }
    while (encodeLine) {
      encodeContentLine(graphics, table, groups, null);
      encodeLine = table.getTotalHeight() < heightLimit;
    }
    table.deleteLastRow();
  }
}
