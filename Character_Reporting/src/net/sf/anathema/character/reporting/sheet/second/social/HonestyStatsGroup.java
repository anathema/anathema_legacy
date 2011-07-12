package net.sf.anathema.character.reporting.sheet.second.social;

import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class HonestyStatsGroup extends AbstractSocialCombatsValueStatsGroup {

  public HonestyStatsGroup(IResources resources, IEquipmentModifiers equipment) {
    super(resources, "Honesty", equipment); //$NON-NLS-1$
  }

  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    if (stats == null) {
      table.addCell(createFinalValueCell(font));
      table.addCell(createFinalValueCell(font));
    }
    else {
      int mpdv = Math.max(0, stats.getHonestyMDV() + equipment.getMPDVMod());
      table.addCell(createFinalValueCell(font, stats.getHonestyAttackValue()));
      table.addCell(createFinalValueCell(font, mpdv));
    }
  }

  public int getColumnCount() {
    return 2;
  }
}