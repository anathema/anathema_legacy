package net.sf.anathema.character.reporting.sheet.second.social;

import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class SocialSpeedStatsGroup extends AbstractSocialCombatsValueStatsGroup {

  public SocialSpeedStatsGroup(IResources resources, IEquipmentModifiers equipment) {
    super(resources, "Speed", equipment); //$NON-NLS-1$
  }

  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    if (stats == null) {
      table.addCell(createFinalValueCell(font));
    }
    else {
      table.addCell(createFinalValueCell(font, stats.getSpeed()));
    }
  }

  public int getColumnCount() {
    return 1;
  }
}