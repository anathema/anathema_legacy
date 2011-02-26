package net.sf.anathema.character.lunar.reporting.knacks;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.lunar.knacks.IKnackStats;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.MagicCostStatsGroup;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.MagicDurationStatsGroup;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.MagicDetailsStatsGroup;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.MagicNameStatsGroup;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.MagicSourceStatsGroup;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.MagicTypeStatsGroup;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractStatsTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.statstable.IStatsGroup;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

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
        new KnackDetailStatsGroup(resources),
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
