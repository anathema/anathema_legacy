package net.sf.anathema.character.reporting.extended.common.magic;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.extended.common.AbstractStatsTableEncoder;
import net.sf.anathema.character.reporting.common.stats.IStatsGroup;
import net.sf.anathema.character.reporting.common.stats.magic.MagicCostStatsGroup;
import net.sf.anathema.character.reporting.common.stats.magic.MagicDetailsStatsGroup;
import net.sf.anathema.character.reporting.common.stats.magic.MagicDurationStatsGroup;
import net.sf.anathema.character.reporting.common.stats.magic.MagicNameStatsGroup;
import net.sf.anathema.character.reporting.common.stats.magic.MagicSourceStatsGroup;
import net.sf.anathema.character.reporting.common.stats.magic.MagicTypeStatsGroup;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PdfMagicTableEncoder extends AbstractStatsTableEncoder<IMagicStats> {

  private final IResources resources;
  private List<IMagicStats> printStats = new ArrayList<IMagicStats>();
  private final boolean sectionHeaderLines;

  public PdfMagicTableEncoder(IResources resources, BaseFont baseFont,
                              List<IMagicStats> printStats) {
    this(resources, baseFont, printStats, false);
  }

  public PdfMagicTableEncoder(IResources resources, BaseFont baseFont,
                              List<IMagicStats> printStats,
                              boolean sectionHeaderLines) {
    super(baseFont, sectionHeaderLines);
    this.resources = resources;
    this.printStats = printStats;
    this.sectionHeaderLines = sectionHeaderLines;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<IMagicStats>[] createStatsGroups(IGenericCharacter character) {
    return new IStatsGroup[] {new MagicNameStatsGroup(resources),
                              new MagicCostStatsGroup(resources),
                              new MagicTypeStatsGroup(resources),
                              new MagicDurationStatsGroup(resources),
                              new MagicDetailsStatsGroup(resources),
                              new MagicSourceStatsGroup(resources)};
  }

  @Override
  protected void encodeContent(PdfPTable table, IGenericCharacter character, Bounds bounds) {
    float heightLimit = bounds.height - 3;
    IStatsGroup<IMagicStats>[] groups = createStatsGroups(character);
    boolean encodeLine = true;
    String groupName = null;
    Collections.sort(printStats);
    for (IMagicStats stats : printStats.toArray(new IMagicStats[printStats.size()])) {
      String newGroupName = stats.getGroupName(resources);
      if (!ObjectUtilities.equals(groupName, newGroupName)) {
        groupName = newGroupName;
        encodeSectionLine(table, groupName);
        if (sectionHeaderLines) {
          encodeHeaderLine(table, groups);
        }
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
        printStats.remove(stats);
      }
    }
    while (encodeLine) {
      encodeContentLine(table, groups, null);
      encodeLine = table.getTotalHeight() < heightLimit;
    }
    table.deleteLastRow();
  }
}
