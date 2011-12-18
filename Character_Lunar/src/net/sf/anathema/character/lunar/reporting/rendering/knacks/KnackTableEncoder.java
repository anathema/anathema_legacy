package net.sf.anathema.character.lunar.reporting.rendering.knacks;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.lunar.reporting.content.stats.knacks.IKnackStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.stats.AbstractStatsTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class KnackTableEncoder extends AbstractStatsTableEncoder<IKnackStats> {

  private final IResources resources;

  public KnackTableEncoder(IResources resources, BaseFont baseFont) {
    super(baseFont);
    this.resources = resources;
  }

  private KnackContent getContent(ReportContent content) {
    return new KnackContent(resources, content.getCharacter());
  }

  @Override
  protected IStatsGroup<IKnackStats>[] createStatsGroups(ReportContent content) {
    KnackContent knackContent = getContent(content);
    return knackContent.createStatsGroups();
  }

  @Override
  protected void encodeContent(PdfPTable table, ReportContent content, Bounds bounds) {
    float heightLimit = bounds.height - 3;
    KnackContent knackContent = getContent(content);
     boolean encodeLine = true;
    String groupName = null;
    IStatsGroup<IKnackStats>[] groups = knackContent.createStatsGroups();
    for (IKnackStats stats : knackContent.createPrintKnacks()) {
      String newGroupName = stats.getGroupName(resources);
      if (!ObjectUtilities.equals(groupName, newGroupName)) {
        groupName = newGroupName;
        encodeSectionLine(table, groupName);
        encodeLine = table.getTotalHeight() < heightLimit;
        if (!encodeLine) {
          table.deleteLastRow();
          return;
        }
      }
      if (encodeLine) {
        encodeContentLine(table, groups, stats);
        encodeLine = table.getTotalHeight() < heightLimit;
        if (!encodeLine) {
          table.deleteLastRow();
          return;
        }
      }
    }
    while (encodeLine) {
      encodeContentLine(table, groups, null);
      encodeLine = table.getTotalHeight() < heightLimit;
    }
    table.deleteLastRow();
  }
}
