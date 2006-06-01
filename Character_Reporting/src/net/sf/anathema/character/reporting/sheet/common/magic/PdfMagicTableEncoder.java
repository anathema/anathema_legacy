package net.sf.anathema.character.reporting.sheet.common.magic;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.IMagicStats;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.MagicCostStatsGroup;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.MagicNameStatsGroup;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.MagicStats;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractStatsTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.statstable.IStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class PdfMagicTableEncoder extends AbstractStatsTableEncoder<IMagicStats> {

  private final IResources resources;
  private List<IMagicStats> printStats = new ArrayList<IMagicStats>();

  public PdfMagicTableEncoder(IResources resources, BaseFont baseFont, List<IMagic> printMagic) {
    super(baseFont);
    this.resources = resources;
    for (IMagic magic : printMagic) {
      printStats.add(new MagicStats(magic));
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<IMagicStats>[] createStatsGroups(IGenericCharacter character) {
    return new IStatsGroup[] { new MagicNameStatsGroup(resources), new MagicCostStatsGroup(resources) };
  }

  @Override
  protected IMagicStats[] getPrintStats(IGenericCharacter character) {
    return printStats.toArray(new IMagicStats[printStats.size()]);
  }

  @Override
  protected IGenericTrait getTrait(IGenericCharacter character, IMagicStats equipment) {
    return null;
  }

  @Override
  protected int getLineCount() {
    return 2;
  }
}