package net.sf.anathema.character.lunar.reporting.content.stats.knacks;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.Resources;

public class KnackNameStatsGroup extends AbstractTextStatsGroup<IKnackStats> {

  private final Resources resources;

  public KnackNameStatsGroup(Resources resources) {
    this.resources = resources;
  }

  @Override
  public Float[] getColumnWeights() {
    return new Float[]{6.0f};
  }

  @Override
  public void addContent(PdfPTable table, Font font, IKnackStats stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, ""));
    } else {
      table.addCell(createTextCell(font, stats.getNameString(resources)));
    }
  }

  @Override
  public String getTitle() {
    return resources.getString("Sheet.Magic.Name");
  }
}
