package net.sf.anathema.character.lunar.reporting.stats.heartsblood;

import net.sf.anathema.character.reporting.common.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class HeartsBloodNotesStatsGroup extends AbstractTextStatsGroup<IHeartsBloodStats> {

  private final IResources resources;

  public HeartsBloodNotesStatsGroup(IResources resources) {
    this.resources = resources;
  }

  public void addContent(PdfPTable table, Font font, IHeartsBloodStats stats) {
    String text = null;//stats == null ? null : stats.getAppearanceString();
    table.addCell(createTextCell(font, text));
  }

  public Float[] getColumnWeights() {
    return new Float[] { 5f };
  }

  public String getTitle() {
    return resources.getString("Sheet.Lunar.HeartsBlood.Notes"); //$NON-NLS-1$
  }
}
