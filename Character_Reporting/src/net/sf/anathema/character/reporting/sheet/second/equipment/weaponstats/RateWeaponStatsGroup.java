package net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats;

import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.reporting.sheet.second.equipment.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class RateWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeapon> {

  public RateWeaponStatsGroup(IResources resources) {
    super(resources, "Rate"); //$NON-NLS-1$
  }

  public int getColumnCount() {
    return 1;
  }

  public void addContent(PdfPTable table, Font font, IGenericTrait trait, IWeapon weapon) {
    if (weapon == null) {
      table.addCell(createFinalValueCell(font));
    }
    else {
      table.addCell(createFinalValueCell(font, weapon.getRate()));
    }
  }
}