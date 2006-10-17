package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.equipment.impl.reporting.second.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public abstract class AbstractSpeedWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  public AbstractSpeedWeaponStatsGroup(IResources resources) {
    super(resources, "Speed"); //$NON-NLS-1$
  }

  public int getColumnCount() {
    return 1;
  }

  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createFinalValueCell(font));
    }
    else {
      table.addCell(createFinalValueCell(font, getSpeedValue(weapon)));
    }
  }

  protected abstract int getSpeedValue(IWeaponStats weapon);
}