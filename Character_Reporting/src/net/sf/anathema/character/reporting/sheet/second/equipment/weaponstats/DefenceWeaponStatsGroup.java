package net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats;

import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.character.reporting.sheet.second.equipment.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class DefenceWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeapon> {

  public DefenceWeaponStatsGroup(IResources resources) {
    super(resources, "Defence"); //$NON-NLS-1$
  }

  public int getColumnCount() {
    return 2;
  }

  public void addContent(PdfPTable table, Font font, IWeapon weapon) {
    table.addCell(createEmptyEquipmentValueCell(font));
    table.addCell(createFinalValueCell(font));
  }
}