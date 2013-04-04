package net.sf.anathema.character.lunar.reporting.content.stats.heartsblood;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.stats.AbstractNameStatsGroup;
import net.sf.anathema.lib.resources.Resources;

public class HeartsBloodNameStatsGroup extends AbstractNameStatsGroup<IHeartsBloodStats> {

  public HeartsBloodNameStatsGroup(Resources resources) {
    super(resources);
  }

  @Override
  protected String getHeaderResourceKey() {
    return "Sheet.Lunar.HeartsBlood.Name";
  }

  @Override
  protected String getResourceBase() {
    return null;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IHeartsBloodStats stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, ""));
    } else {
      String name = stats.getName().getId();
      table.addCell(createTextCell(font, name));
    }
  }
}
