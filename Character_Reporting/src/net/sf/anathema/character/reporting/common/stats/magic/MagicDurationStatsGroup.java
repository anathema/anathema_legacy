package net.sf.anathema.character.reporting.common.stats.magic;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.common.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public class MagicDurationStatsGroup extends AbstractTextStatsGroup<IMagicStats> {

  private final IResources resources;

  public MagicDurationStatsGroup(IResources resources) {
    this.resources = resources;
  }

  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    String text = stats == null ? null : stats.getDurationString(resources);
    table.addCell(createTextCell(font, text));
  }

  public Float[] getColumnWeights() {
    return new Float[]{3f};
  }

  public String getTitle() {
    return resources.getString("Sheet.Magic.Duration"); //$NON-NLS-1$
  }
}
