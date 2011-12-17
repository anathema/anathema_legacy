package net.sf.anathema.character.reporting.common.stats.social;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.lib.resources.IResources;

public class DeceptionStatsGroup extends AbstractSocialCombatsValueStatsGroup {

  public DeceptionStatsGroup(IResources resources) {
    super(resources, "Deception"); //$NON-NLS-1$
  }

  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    if (stats == null) {
      table.addCell(createFinalValueCell(font));
      table.addCell(createFinalValueCell(font));
    }
    else {
      table.addCell(createFinalValueCell(font, stats.getDeceptionAttackValue()));
      table.addCell(createFinalValueCell(font, stats.getDeceptionMDV()));
    }
  }

  public int getColumnCount() {
    return 2;
  }
}
