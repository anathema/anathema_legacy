package net.sf.anathema.character.reporting.common.stats.magic;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.common.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.IResources;

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
    return new Float[]{2.5f};
  }

  public String getTitle() {
    return resources.getString("Sheet.Magic.Type"); //$NON-NLS-1$
  }
}
