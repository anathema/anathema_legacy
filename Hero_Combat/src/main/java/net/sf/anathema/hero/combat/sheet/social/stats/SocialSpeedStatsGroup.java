package net.sf.anathema.hero.combat.sheet.social.stats;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.lib.resources.Resources;

public class SocialSpeedStatsGroup extends AbstractSocialCombatsValueStatsGroup {

  public SocialSpeedStatsGroup(Resources resources) {
    super(resources, "Speed");
  }

  @Override
  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    if (stats == null) {
      table.addCell(createFinalValueCell(font));
    } else {
      table.addCell(createFinalValueCell(font, stats.getSpeed()));
    }
  }

  @Override
  public int getColumnCount() {
    return 1;
  }
}
