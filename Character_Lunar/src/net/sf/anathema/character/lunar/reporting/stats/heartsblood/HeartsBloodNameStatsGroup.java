package net.sf.anathema.character.lunar.reporting.stats.heartsblood;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

import net.sf.anathema.character.reporting.common.stats.AbstractNameStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public class HeartsBloodNameStatsGroup extends AbstractNameStatsGroup<IHeartsBloodStats> {

  public HeartsBloodNameStatsGroup(IResources resources) {
    super(resources);
  }

  @Override
  protected String getHeaderResourceKey() {
    return "Sheet.Lunar.HeartsBlood.Name"; //$NON-NLS-1$
  }

  @Override
  protected String getResourceBase() {
    return null;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IHeartsBloodStats stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, "")); //$NON-NLS-1$
    }
    else {
      String name = stats.getName().getId();
      table.addCell(createTextCell(font, name));
    }
  }
}
