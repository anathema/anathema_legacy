package net.sf.anathema.character.reporting.pdf.content.stats.magic;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public class MagicCostStatsGroup extends AbstractTextStatsGroup<IMagicStats> {

  private final IResources resources;

  public MagicCostStatsGroup(IResources resources) {
    this.resources = resources;
  }

  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    String text = stats == null ? null : stats.getCostString(resources);
    table.addCell(createTextCell(font, text));
  }

  public Float[] getColumnWeights() {
    return new Float[]{3.0f};
  }

  public String getTitle() {
    return resources.getString("Sheet.Magic.Cost"); //$NON-NLS-1$
  }
}
