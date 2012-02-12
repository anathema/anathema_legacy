package net.sf.anathema.character.reporting.pdf.content.stats.social;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.lib.resources.IResources;

public class SocialRateStatsGroup extends AbstractSocialCombatsValueStatsGroup {

  public SocialRateStatsGroup(IResources resources) {
    super(resources, "Rate"); //$NON-NLS-1$
  }

  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    if (stats == null) {
      table.addCell(createFinalValueCell(font));
    } else {
      table.addCell(createFinalValueCell(font, stats.getRate()));
    }
  }

  public int getColumnCount() {
    return 1;
  }
}
