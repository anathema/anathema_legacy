package net.sf.anathema.character.reporting.common.stats;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractNameStatsGroup<T extends IStats> extends AbstractTextStatsGroup<T> {
  private final String title;
  private final IResources resources;

  public AbstractNameStatsGroup(IResources resources) {
    this.resources = resources;
    this.title = resources.getString(getHeaderResourceKey());
  }

  public final String getTitle() {
    return title;
  }

  public Float[] getColumnWeights() {
    return new Float[]{new Float(6)};
  }

  public void addContent(PdfPTable table, Font font, T stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, "")); //$NON-NLS-1$
    }
    else {
      String resourceKey = getResourceBase() + stats.getName().getId();
      table.addCell(createTextCell(font, resources.getString(resourceKey)));
    }
  }

  protected abstract String getHeaderResourceKey();

  protected abstract String getResourceBase();
}
