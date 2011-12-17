package net.sf.anathema.character.lunar.reporting.sheet;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.lunar.reporting.stats.knacks.IKnackStats;
import net.sf.anathema.character.lunar.reporting.stats.knacks.KnackNameStatsGroup;
import net.sf.anathema.character.lunar.reporting.stats.knacks.KnackSourceStatsGroup;
import net.sf.anathema.character.reporting.sheet.common.AbstractStatsTableEncoder;
import net.sf.anathema.character.reporting.stats.IStatsGroup;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;

public class KnackTableEncoder extends AbstractStatsTableEncoder<IKnackStats> {

  private final IResources resources;
  private List<IKnackStats> printKnacks = new ArrayList<IKnackStats>();

  public KnackTableEncoder(IResources resources, BaseFont baseFont, List<IKnackStats> printKnacks)
  {
    super(baseFont);
    this.resources = resources;
    this.printKnacks = printKnacks;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<IKnackStats>[] createStatsGroups(IGenericCharacter character) {
    return new IStatsGroup[] {
        new KnackNameStatsGroup(resources),
        new KnackSourceStatsGroup(resources) };
  }

  @Override
  protected void encodeContent(PdfPTable table, IGenericCharacter character, Bounds bounds) {
    float heightLimit = bounds.height - 3;
    IStatsGroup<IKnackStats>[] groups = createStatsGroups(character);
    boolean encodeLine = true;
    String groupName = null;
    for (IKnackStats stats : printKnacks)
    {
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
