package net.sf.anathema.hero.magic.sheet.content.stats;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.main.magic.model.magic.IMagicStats;
import net.sf.anathema.hero.sheet.pdf.content.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.Resources;

public class MagicDetailsStatsGroup extends AbstractTextStatsGroup<IMagicStats> {

  private final Resources resources;

  public MagicDetailsStatsGroup(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, null));
    } else {
      String[] details = stats.getDetailStrings(resources);
      String detailText = Joiner.on(", ").join(details);
      if (Strings.isNullOrEmpty(detailText)) {
        detailText = "-";
      }
      table.addCell(createTextCell(font, detailText));
    }
  }

  @Override
  public Float[] getColumnWeights() {
    return new Float[]{7f};
  }

  @Override
  public String getTitle() {
    return resources.getString("Sheet.Magic.Details");
  }
}
