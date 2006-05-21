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
    table.addCell(createEquipmentValueCell(font));
    table.addCell(createFinalValueCell(font));
  }
}