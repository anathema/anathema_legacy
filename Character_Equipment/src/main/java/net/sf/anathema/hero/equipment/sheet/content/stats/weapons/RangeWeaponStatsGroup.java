package net.sf.anathema.hero.equipment.sheet.content.stats.weapons;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.lib.resources.Resources;

public class RangeWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  public RangeWeaponStatsGroup(Resources resources) {
    super(resources, "Range");
  }

  @Override
  public int getColumnCount() {
    return 1;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createFinalValueCell(font));
    } else {
      table.addCell(createFinalValueCell(font, weapon.getRange()));
    }
  }
}
