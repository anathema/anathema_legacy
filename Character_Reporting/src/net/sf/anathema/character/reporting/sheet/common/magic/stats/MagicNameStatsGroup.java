package net.sf.anathema.character.reporting.sheet.common.magic.stats;

import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class MagicNameStatsGroup extends AbstractTextStatsGroup<IMagicStats> {

  private final IResources resources;

  public MagicNameStatsGroup(IResources resources) {
    this.resources = resources;
  }

  public Float[] getColumnWeights() {
    return new Float[] { 6.0f };
  }

  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, "")); //$NON-NLS-1$
    }
    else {
      table.addCell(createTextCell(font, stats.getNameString(resources)));
    }
  }

  public String getTitle() {
    return resources.getString("Sheet.Magic.Name"); //$NON-NLS-1$
  }
}