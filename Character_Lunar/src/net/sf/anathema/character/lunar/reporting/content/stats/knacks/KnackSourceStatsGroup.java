package net.sf.anathema.character.lunar.reporting.content.stats.knacks;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.Resources;

public class KnackSourceStatsGroup extends AbstractTextStatsGroup<IKnackStats> {

  private final Resources resources;

  public KnackSourceStatsGroup(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IKnackStats stats) {
    String text = stats == null ? null : stats.getSourceString(resources);
    table.addCell(createTextCell(font, text));
  }

  @Override
  public Float[] getColumnWeights() {
    return new Float[]{2.0f};
  }

  @Override
  public String getTitle() {
    return resources.getString("Sheet.Magic.Source");
  }
}
