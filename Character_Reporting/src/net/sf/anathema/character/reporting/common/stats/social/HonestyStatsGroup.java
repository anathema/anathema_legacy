package net.sf.anathema.character.reporting.common.stats.social;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.lib.resources.IResources;

public class HonestyStatsGroup extends AbstractSocialCombatsValueStatsGroup {

  public HonestyStatsGroup(IResources resources) {
    super(resources, "Honesty"); //$NON-NLS-1$
  }

  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    if (stats == null) {
      table.addCell(createFinalValueCell(font));
      table.addCell(createFinalValueCell(font));
    }
    else {
      table.addCell(createFinalValueCell(font, stats.getHonestyAttackValue()));
      table.addCell(createFinalValueCell(font, stats.getHonestyMDV()));
    }
  }

  public int getColumnCount() {
    return 2;
  }
}
