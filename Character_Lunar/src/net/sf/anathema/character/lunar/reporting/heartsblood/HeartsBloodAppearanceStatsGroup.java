package net.sf.anathema.character.lunar.reporting.heartsblood;

import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class HeartsBloodAppearanceStatsGroup extends AbstractTextStatsGroup<IHeartsBloodStats> {

  private final IResources resources;

  public HeartsBloodAppearanceStatsGroup(IResources resources) {
    this.resources = resources;
  }

  public void addContent(PdfPTable table, Font font, IHeartsBloodStats stats) {
    String text = stats == null ? null : stats.getAppearanceString();
    table.addCell(createTextCell(font, text));
  }

  public Float[] getColumnWeights() {
    return new Float[] { 1.5f };
  }

  public String getTitle() {
    return resources.getString("Sheet.Lunar.HeartsBlood.Appearance"); //$NON-NLS-1$
  }
}