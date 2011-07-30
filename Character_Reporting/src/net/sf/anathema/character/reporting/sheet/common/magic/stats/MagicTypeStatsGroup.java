package net.sf.anathema.character.reporting.sheet.common.magic.stats;

import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class MagicTypeStatsGroup extends AbstractTextStatsGroup<IMagicStats> {

  private final IResources resources;

  public MagicTypeStatsGroup(IResources resources) {
    this.resources = resources;
  }

  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    String text = stats == null ? null : stats.getType(resources);
    table.addCell(createTextCell(font, text));
  }

  public Float[] getColumnWeights() {
    return new Float[] { 3f };
  }

  public String getTitle() {
    return resources.getString("Sheet.Magic.Type"); //$NON-NLS-1$
  }
}