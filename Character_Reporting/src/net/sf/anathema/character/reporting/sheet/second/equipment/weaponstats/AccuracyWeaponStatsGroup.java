package net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats;

import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.character.reporting.sheet.second.equipment.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class AccuracyWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeapon> {

  public AccuracyWeaponStatsGroup(IResources resources) {
    super(resources, "Accuracy"); //$NON-NLS-1$
  }

  public int getColumnCount() {
    return 2;
  }

  public void addContent(PdfPTable table, Font font, IWeapon weapon) {
    if (weapon == null) {
      table.addCell(createEmptyEquipmentValueCell(font));
      table.addCell(createFinalValueCell(font));
    }
    else {
      table.addCell(createEquipmentValueCell(font, weapon.getAccuracy()));
      // todo vom (21.05.2006) (sieroux): Hier brauch's noch den Character
      table.addCell(createFinalValueCell(font));
    }
  }
}