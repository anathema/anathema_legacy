package net.sf.anathema.character.reporting.sheet.second.social;

import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class DeceptionStatsGroup extends AbstractSocialCombatsValueStatsGroup {

  public DeceptionStatsGroup(IResources resources, IEquipmentModifiers equipment) {
    super(resources, "Deception", equipment); //$NON-NLS-1$
  }

  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    if (stats == null) {
      table.addCell(createFinalValueCell(font));
      table.addCell(createFinalValueCell(font));
    }
    else {
      int mpdv = Math.max(0, stats.getDeceptionMDV() + equipment.getMPDVMod());
      table.addCell(createFinalValueCell(font, stats.getDeceptionAttackValue()));
      table.addCell(createFinalValueCell(font, mpdv));
    }
  }

  public int getColumnCount() {
    return 2;
  }
}