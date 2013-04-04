package net.sf.anathema.character.reporting.pdf.content.stats.social;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.lib.resources.Resources;

public class SocialRateStatsGroup extends AbstractSocialCombatsValueStatsGroup {

  public SocialRateStatsGroup(Resources resources) {
    super(resources, "Rate");
  }

  @Override
  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    if (stats == null) {
      table.addCell(createFinalValueCell(font));
    } else {
      table.addCell(createFinalValueCell(font, stats.getRate()));
    }
  }

  @Override
  public int getColumnCount() {
    return 1;
  }
}
