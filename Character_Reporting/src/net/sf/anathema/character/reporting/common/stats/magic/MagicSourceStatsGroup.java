package net.sf.anathema.character.reporting.common.stats.magic;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.common.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public class MagicSourceStatsGroup extends AbstractTextStatsGroup<IMagicStats> {

  private final IResources resources;

  public MagicSourceStatsGroup(IResources resources) {
    this.resources = resources;
  }

  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    String text = stats == null ? null : stats.getSourceString(resources);
    table.addCell(createTextCell(font, text));
  }

  public Float[] getColumnWeights() {
    return new Float[]{2.0f};
  }

  public String getTitle() {
    return resources.getString("Sheet.Magic.Source"); //$NON-NLS-1$
  }
}
