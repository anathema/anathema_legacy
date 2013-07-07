package net.sf.anathema.hero.magic.sheet.content.stats;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.main.magic.IMagicStats;
import net.sf.anathema.hero.sheet.pdf.content.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.Resources;

public class MagicNameStatsGroup extends AbstractTextStatsGroup<IMagicStats> {

  private final Resources resources;

  public MagicNameStatsGroup(Resources resources) {
    this.resources = resources;
  }

  @Override
  public Float[] getColumnWeights() {
    return new Float[]{6.0f};
  }

  @Override
  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
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
