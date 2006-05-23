package net.sf.anathema.character.equipment.impl.reporting.second.weaponstats;

import net.sf.anathema.character.equipment.impl.reporting.second.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.character.generic.traits.IGenericTrait;
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

  public void addContent(PdfPTable table, Font font, IWeapon weapon, IGenericTrait... traits) {
    if (weapon == null) {
      table.addCell(createEmptyEquipmentValueCell(font));
      table.addCell(createFinalValueCell(font));
    }
    else {
      table.addCell(createEquipmentValueCell(font, weapon.getDefence()));
      if (weapon.getDefence() == null) {
        table.addCell(createFinalValueCell(font, (Integer) null));
      }
      else {
        table.addCell(createFinalValueCell(font, calculateFinalValue(weapon.getDefence(), traits)));
      }
    }
  }
}